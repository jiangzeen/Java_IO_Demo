package com.notepad;
import java.io.*;
import java.util.*;
/**
 * 
 * @ClassName: IONotepad
 * @描述:用IO流实现一个控制台版本的记事本
 * @作者:蒋泽恩
 * @时间:2018年4月1日 上午9:25:25
 */
public class IONotepad {
    private static String filepath;
    private static String message;
	public static void main(String[] args) throws Exception {
		 Scanner sc = new Scanner(System.in);
		 System.out.println("--1.新建文件 2.打开文件 3.修改文件  4.保存 5.退出 --");
		 while(true) {
			 System.out.println("请输入操作指令:");
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
				System.out.println("你输入的指令有误!!!");
				break;
			 }
		 }
	}
	private static void creatfile() {
		message = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入内容,停止编写请输入\"stop\":");
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
		System.out.println("请输入要打开文件的位置:");
		filepath = sc.next();
		if(filepath != null && !filepath.endsWith(".txt")) {
			System.out.println("请选择文本文件!");
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
		System.out.println("打开文件内容:"+"\r\n"+message);
		fr.close();
	}
    /**
     * 修改文件内容,以字符串替换的形式
     */
	private static void editfile() {
		if(message == "" && filepath == null) {
			System.out.println("请先新建文件或者打开文件");
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入要修改的内容(以\"修改的目标文字:修改之后的文字\"格式),"+"停止修改请输入\"stop\":");
		String inputMessage = "";
		while(!inputMessage.equals("stop")) {
			inputMessage = sc.nextLine();
			if(inputMessage != null && inputMessage.length() > 1) {
				//将输入的文字根据“:”拆分为两个数组
				String[] editMessage = inputMessage.split(":");
				if(editMessage != null && editMessage.length > 1) {
					message = message.replace(editMessage[0], editMessage[1]);
				}
			}
		}
		System.out.println("修改后的内容:" + "\r\n" + message);
	}
	/**
	 * 保存  新建文本存在用户输入的目录下,然后打开原文件进行覆盖
	 * @throws IOException 
	 */
	private static void savefile() throws IOException {
		Scanner sc =new Scanner(System.in);
		FileWriter out =null;
		if(filepath != null) {
			out = new FileWriter(filepath);
		}else {
			System.out.println("请输入文件保存的绝对地址:");
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
		System.out.println("系统已经退出,谢谢使用 ！");
		System.exit(0);
	}
}