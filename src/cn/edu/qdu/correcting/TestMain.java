package cn.edu.qdu.correcting;

public class TestMain {

	public static void main(String[] args) {
		new CorrectingPapers("e:/stu/", "e:/answer.txt").getGradesFile("e:/greades.txt");
	}

}
