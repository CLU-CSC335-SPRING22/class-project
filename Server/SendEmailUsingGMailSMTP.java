package Server;

/*
 * Goto icon->Manage Your Google Account->Security->Less secure app access (ON)
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// -- Download JavaMail API from here: http://www.oracle.com/technetwork/java/javamail/index.html
// -- Download JavaBeans Activation Framework from here: http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-java-plat-419418.html#jaf-1.1.1-fcs-oth-JPR
//    Activation Framework is only needed for earlier versions of Java
// -- Your gmail account must be set to allow "less secure apps" to access it
//    Information is found here: https://devanswers.co/allow-less-secure-apps-access-gmail-account/
// -- javax.mail.jar moved here: https://javaee.github.io/javamail/?xd_co_f=c9a74338-3e62-4b48-9e18-489fa570fd5b
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailUsingGMailSMTP {

	// -- set the gmail host URL
	final static private String host = "smtp.gmail.com";
	final static private String port = "587";

	// -- You must have a valid gmail username/password pair to use
	// gmail as a SMTP service
	final static private String gmailusername = System.getenv("EMAILUSERNAME");
	final static private String gmailpassword = System.getenv("EMAILPASSWORD");
	
	public static void main(String[] args) {
        // -- comma separated values of to email addresses
        String to = "tgilmore@gmail.com";
		sendMail(to);
	}
	
    public static void sendMail(String to) {
        // -- Configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        // -- Create a session with required user details
        //    this is basically logging into the gmail account
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(gmailusername, gmailpassword);
            }
        });
        try {
            //-- create the Message to be sent
            MimeMessage msg = new MimeMessage(session);

            // -- get the internet addresses for the recipients
            InternetAddress[] address = InternetAddress.parse(to, true);
            
            // -- set the recipients
            msg.setRecipients(Message.RecipientType.TO, address);
            
            // -- set the subject line (time stamp)
            Calendar cal = Calendar.getInstance();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(cal.getTime());
            msg.setSubject("Sample Mail : " + timeStamp);
            msg.setSentDate(new Date());
                        
            // -- set the message text
            msg.setText("Here is your new password: Adlwod332d");
            msg.setHeader("XPriority", "1");
            
            // -- send the message
            Transport.send(msg);
            
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException e) {
            System.out.println("Unable to send an email" + e);
        }
    }
}
