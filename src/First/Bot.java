package First;
import java.util.Scanner;

public class Bot implements MessageListener {
	private final String help = "� ������� ���. ����� �������� ������ ���, � ������� � ���� ������, ������ \"����\".\n"
			+ "��� ������ ���� ������ � ��������.";
	
	public Bot() {
		start();
	}
	
	public void start() {
		System.out.println("������, ������������!");
		System.out.println(help);
	}
	
	public void getGames() {
		
	}

	@Override
	public void onMessage(String message) {
		System.out.println("��������� �������! " + message);
	}
}
