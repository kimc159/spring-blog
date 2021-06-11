package com.project.chat.mail;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Service
public class MailSendService {
    @Autowired
    private JavaMailSenderImpl mailSender;
    
    private int size;
    
    //����Ű ����
    private String getKey(int size) {
        this.size = size;
        return getAuthCode();
    }

    //�����ڵ� ���� �߻�
    private String getAuthCode() {
    	 
    	Random ran = new Random();
    	StringBuffer buffer = new StringBuffer();
    	
    	int num = 0;
    	while(buffer.length() < size) {
    		num = ran.nextInt(10);
    		buffer.append(num);
    	}
    	
    	return buffer.toString();
    	
    }
    //�������� ������
    public String sendAuthMail(String email) {
        //6�ڸ� ���� ������ȣ ����
        String authKey = getKey(6);

        //�������� ������
        try {
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("ȸ������ �̸��� ����");
            sendMail.setText(new StringBuffer().append("<h1>[�̸��� ����]</h1>")
            .append("<p>�Ʒ� ��ũ�� Ŭ���Ͻø� �̸��� ������ �Ϸ�˴ϴ�.</p>")
            .append("<a href='http://192.168.0.25:8090/join/confirm?email=")
            .append(email)
            .append("&authKey=") 
            .append(authKey)
            .append("' target='_blenk'>�̸��� ���� Ȯ��</a>")
            .toString());
            sendMail.setFrom("ljykimc159@gmail.com", "관리자");
            sendMail.setTo(email);
            sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

          return authKey;
    }
}