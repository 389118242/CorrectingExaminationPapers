package cn.edu.qdu.correcting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CorrectingPapers {
	private String studentsAnswerPath;
	private String answerPath;

	public CorrectingPapers(String studentsAnswerPath, String answerPath) {
		this.studentsAnswerPath = studentsAnswerPath;
		this.answerPath = answerPath;
	}

	private List<String> getAnswerList(String path) {
		List<String> answer = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			answer = new ArrayList<>();
			String line = null;
			while ((line = in.readLine()) != null) {
				answer.add(line);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}

	public void getGradesFile(String Path) {
		File[] stuAndwerFiles = new File(studentsAnswerPath).listFiles();
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(Path));
			for (int i = 0; i < stuAndwerFiles.length; i++) {
				File file = stuAndwerFiles[i];
				// if (!new File(answerPath).getName().equals(file.getName())) {
				int greads = compareToRightAnswer(getAnswerList(file.getAbsolutePath()));
				if (i == 0) {
					out.write("ÐÕÃû\t³É¼¨\r\n");
				}
				String name = file.getName().substring(0, file.getName().length() - 4);
				out.write(name + "\t" + greads);
				if (i < stuAndwerFiles.length - 1) {
					out.newLine();
				}
				// }
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int compareToRightAnswer(List<String> stuAnswer) {
		int greades = 0;
		List<String> answer = getAnswerList(answerPath);
		int minSize = answer.size() > stuAnswer.size() ? stuAnswer.size() : answer.size();
		for (int i = 0; i < minSize; i++) {
			byte[] stuAnswerByte = stuAnswer.get(i).getBytes();
			byte[] answerByte = answer.get(i).getBytes();
			for (int j = 0; j < (stuAnswerByte.length > answerByte.length ? answerByte.length
					: stuAnswerByte.length); j++) {
				if (stuAnswerByte[j] == answerByte[j])
					greades++;
			}
		}
		return greades;
	}

	public String getStudentsAnswerPath() {
		return studentsAnswerPath;
	}

	public void setStudentsAnswerPath(String studentsAnswerPath) {
		this.studentsAnswerPath = studentsAnswerPath;
	}

	public String getAnswerPath() {
		return answerPath;
	}

	public void setAnswerPath(String answerPath) {
		this.answerPath = answerPath;
	}

}
