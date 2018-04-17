package com.notepad;
import java.io.*;
import java.util.*;
/**
 * 
 * @ClassName: IONotepad
 * @����:��IO��ʵ��һ������̨�汾�ļ��±�
 * @����:�����
 * @ʱ��:2018��4��1�� ����9:25:25
 */
public class IONotepad {
    private static String filepath;
    private static String message;
	public static void main(String[] args) throws Exception {
		 Scanner sc = new Scanner(System.in);
		 System.out.println("--1.�½��ļ� 2.���ļ� 3.�޸��ļ�  4.���� 5.�˳� --");
		 while(true) {
			 System.out.println("���������ָ��:");
			 int order = sc.nextInt();
			 switch(order)
			 {
			 case 1:
				 creatfile();
				 break;
			 case 2:
				 openfile();
				 break;
			 case 3:
				 editfile();
				 break;
			 case 4:
				 savefile();
				 break;
			 case 5:
				 exit();
				 break;
			default:
				System.out.println("�������ָ������!!!");
				break;
			 }
		 }
	}
	private static void creatfile() {
		message = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("����������,ֹͣ��д������\"stop\":");
		StringBuffer sb = new StringBuffer();
		String inputMessage = "";
		while(!inputMessage.equals("stop")) {
			if(sb.length() > 0) {
				sb.append("\r\n");
			}
			sb.append(inputMessage);
			inputMessage = sc.nextLine();
		}
		message = sb.toString();
	}

	private static void openfile() throws Exception {
		message = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("������Ҫ���ļ���λ��:");
		filepath = sc.next();
		if(filepath != null && !filepath.endsWith(".txt")) {
			System.out.println("��ѡ���ı��ļ�!");
			return;
		}
		FileReader fr = new FileReader(filepath);
		char[] charArray = new char[1024];
		int len = 0;
		StringBuffer st = new StringBuffer();
		while((len = fr.read(charArray)) != -1) {
			st.append(charArray);
		}
		message = st.toString();
		System.out.println("���ļ�����:"+"\r\n"+message);
		fr.close();
	}
    /**
     * �޸��ļ�����,���ַ����滻����ʽ
     */
	private static void editfile() {
		if(message == "" && filepath == null) {
			System.out.println("�����½��ļ����ߴ��ļ�");
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("������Ҫ�޸ĵ�����(��\"�޸ĵ�Ŀ������:�޸�֮�������\"��ʽ),"+"ֹͣ�޸�������\"stop\":");
		String inputMessage = "";
		while(!inputMessage.equals("stop")) {
			inputMessage = sc.nextLine();
			if(inputMessage != null && inputMessage.length() > 1) {
				//����������ָ��ݡ�:�����Ϊ��������
				String[] editMessage = inputMessage.split(":");
				if(editMessage != null && editMessage.length > 1) {
					message = message.replace(editMessage[0], editMessage[1]);
				}
			}
		}
		System.out.println("�޸ĺ������:" + "\r\n" + message);
	}
	/**
	 * ����  �½��ı������û������Ŀ¼��,Ȼ���ԭ�ļ����и���
	 * @throws IOException 
	 */
	private static void savefile() throws IOException {
		Scanner sc =new Scanner(System.in);
		FileWriter out =null;
		if(filepath != null) {
			out = new FileWriter(filepath);
		}else {
			System.out.println("�������ļ�����ľ��Ե�ַ:");
			String path = sc.next();
			filepath = path;
			
			if(!filepath.toLowerCase().endsWith(".txt")) {
				filepath += ".txt"; 
			}
			out = new FileWriter(filepath);
		}
		out.write(message);
		out.close();
		message = "";
		filepath = null;
	}

	private static void exit() {
		System.out.println("ϵͳ�Ѿ��˳�,ллʹ�� ��");
		System.exit(0);
	}
}