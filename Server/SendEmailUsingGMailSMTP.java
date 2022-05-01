package Server;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailUsingGMailSMTP {

	final static private String host = "smtp.gmail.com";
	final static private String port = "587";

	final static private String gmailusername = System.getenv("EMAILUSERNAME");
	final static private String gmailpassword = System.getenv("EMAILPASSWORD");
	
	public static void main(String[] args) {
        String to = "";// Query for username locked goes here
		sendTempPass(to);
	}
	
    public static void sendTempPass(String to) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(gmailusername, gmailpassword);
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);

            InternetAddress[] address = InternetAddress.parse(to, true);

            msg.setRecipients(Message.RecipientType.TO, address);

            Calendar cal = Calendar.getInstance();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(cal.getTime());
            msg.setSubject("Sample Mail : " + timeStamp);
            msg.setSentDate(new Date());

            msg.setText("Here is your new password: Adlwod332d");
            msg.setHeader("XPriority", "1");

            Transport.send(msg);
            
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException e) {
            System.out.println("Unable to send an email" + e);
        }
    }
}
