package com.robo.alice.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
	private static final Logger log = LoggerFactory.getLogger(QuestionService.class);

	@Value("${question.file:data.txt}")
	private String questionFilePath;

	private final List<List<String>> questions = new ArrayList<>();
	private boolean loadDone;

	private synchronized void ensureLoadDone() {
		if (loadDone) {
			return;
		}
		loadDone = true;
		if (!loadFromWorkingDir()) {
			loadFromResources();
		}
	}

	private boolean loadFromResources() {
		try {
			InputStream in = new ClassPathResource(questionFilePath).getInputStream();
			loadData(new BufferedReader(new InputStreamReader(in)));
			log.info("Loaded data from resources");
			return true;
		} catch (IOException ie) {
			log.error("Error reading question data file from resources", ie);
			return false;
		}
	}

	private boolean loadFromWorkingDir() {
		try {
			InputStream in = new FileSystemResource(questionFilePath).getInputStream();
			loadData(new BufferedReader(new InputStreamReader(in)));
			log.info("Loaded data from working dir");
			return true;
		} catch (FileNotFoundException fe) {
			log.info("Question data file not found in working dir, will load from classpath.");
		} catch (IOException ie) {
			log.error("Error reading question data file from working dir, attempting to read from classpath.", ie);
		}
		return false;
	}

	private void loadData(BufferedReader br) throws IOException {
		String question = br.readLine();
		while (question != null) {
			String answer = br.readLine();
			if (answer == null) {
				question = null;
			} else {
				List<String> questionData = new ArrayList<String>();
				questionData.add(question);
				questionData.add(answer);
				questions.add(questionData);
				question = br.readLine();
			}
		}
		br.close();
		log.info("Loaded question data from file " + questionFilePath);
	}

	public List<List<String>> getAllQuestions() {
		ensureLoadDone();
		return questions;
	}
}
