package org.cap.demo.util;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public class EmailService {
	
	@Autowired
	JavaMailSender mailSender;
	
	//@Async tells spring not to wait until the email was sent. 
	@Async
	public void sendEmail(String to,String resetToken) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setFrom("satishkumpatla1999@gmail.com");
		helper.setSubject("Link to reset your password");
		
		String link = "http://localhost:4200/reset_password?token="+resetToken;
		
		
		String body = """
				<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- <link rel="stylesheet" href="forgotpassword.css"> -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fira+Sans+Condensed:wght@200&display=swap" rel="stylesheet">

    <style>
        body{
    font-family: 'Fira Sans Condensed', sans-serif;
}
.head-section{
     text-align: center; 
     margin-top: 2rem;
}
.header{
    
    color: rgb(215,220,222);
    padding: 0.12em;
    background-color: #0c344c;
    font-weight: 600;
    
}
.subheader{
    color: #0c344c;
    padding: 0.075em;
    border: #0c344c 2px solid;
}
.section1{
    text-align: center;
    background-color: #0c344c;
    color: rgb(215,220,222);
    font-size: 2rem;
    padding-top: 1rem;
    margin-top: 1rem;
}

.section2{
    display: grid;
}

.section2 p{
    line-height: 3rem;
    padding-left: 15%;
    font-size: 1.67rem;
    color: #0c344c;
    
    
}

.reset-button{
	text-decoration:none;
    border: none;
    color: white;
    background-color: #0c344c;
    font-size: 1.67rem;
    padding: 0.33rem;
    margin: auto;
}
.footer{
    background-color: #0c344c;
    color: rgb(215,220,222);
    line-height: 1rem;
}
.center-flow{
display: flex;
 justify-content: space-evenly;
}
.contact-us{
    margin-top: 0.5rem;
    margin-left:4rem;
}
.copy-rights{
    margin-top: 0.5rem;
    margin-left:14rem;
}
</style>

</head>

<body>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<!-- <link rel="stylesheet" href="forgotpassword.css"> -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Fira+Sans+Condensed:wght@200&display=swap" rel="stylesheet">


    <!--rgba(10,49,77,255)  rgb(164,180,188)  rgb(215,220,222)-->
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="head-section">
                    <h1><span class="header">NGT</span><span class="subheader">Insurance</span></h1>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="section1">
                    <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="currentColor"
                        class="bi bi-shield-lock" viewBox="0 0 16 16">
                        <path
                            d="M5.338 1.59a61.44 61.44 0 0 0-2.837.856.481.481 0 0 0-.328.39c-.554 4.157.726 7.19 2.253 9.188a10.725 10.725 0 0 0 2.287 2.233c.346.244.652.42.893.533.12.057.218.095.293.118a.55.55 0 0 0 .101.025.615.615 0 0 0 .1-.025c.076-.023.174-.061.294-.118.24-.113.547-.29.893-.533a10.726 10.726 0 0 0 2.287-2.233c1.527-1.997 2.807-5.031 2.253-9.188a.48.48 0 0 0-.328-.39c-.651-.213-1.75-.56-2.837-.855C9.552 1.29 8.531 1.067 8 1.067c-.53 0-1.552.223-2.662.524zM5.072.56C6.157.265 7.31 0 8 0s1.843.265 2.928.56c1.11.3 2.229.655 2.887.87a1.54 1.54 0 0 1 1.044 1.262c.596 4.477-.787 7.795-2.465 9.99a11.775 11.775 0 0 1-2.517 2.453 7.159 7.159 0 0 1-1.048.625c-.28.132-.581.24-.829.24s-.548-.108-.829-.24a7.158 7.158 0 0 1-1.048-.625 11.777 11.777 0 0 1-2.517-2.453C1.928 10.487.545 7.169 1.141 2.692A1.54 1.54 0 0 1 2.185 1.43 62.456 62.456 0 0 1 5.072.56z" />
                        <path
                            d="M9.5 6.5a1.5 1.5 0 0 1-1 1.415l.385 1.99a.5.5 0 0 1-.491.595h-.788a.5.5 0 0 1-.49-.595l.384-1.99a1.5 1.5 0 1 1 2-1.415z" />
                    </svg>
                    <p>Link to reset your password</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="section2">
                    <p>
                        Hello,
                        <br>
                        We sent you this email in response to your request to reset your password.
                        <br>
                        Please click the button below to reset your password:
                    </p>"""
				+ "<a class=\"reset-button\" href=\"" + link + "\">Change my password</a>"
				+"""
                    
                    
                    <br>
                    <p><em>Please ignore this email if you did not request a password change.</em></p>
                </div>
            </div>
        </div>
        <div class="row footer center-flow">
            <div class="col-6">
                <div class="contact-us ">
                <p>Contact Us</p>
                <p>Farmers Digital Marketing NGT Team</p>
                </div>
            </div>
            <div class="col-6 ">
                <div class="copy-rights">
                <p>NGT Insurance</p>
                <p>Copyright&nbsp;<span><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-c-circle" viewBox="0 0 16 16">
                            <path
                                d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM8.146 4.992c-1.212 0-1.927.92-1.927 2.502v1.06c0 1.571.703 2.462 1.927 2.462.979 0 1.641-.586 1.729-1.418h1.295v.093c-.1 1.448-1.354 2.467-3.03 2.467-2.091 0-3.269-1.336-3.269-3.603V7.482c0-2.261 1.201-3.638 3.27-3.638 1.681 0 2.935 1.054 3.029 2.572v.088H9.875c-.088-.879-.768-1.512-1.729-1.512Z" />
                        </svg>
                        
                        <i class="bi bi-c-circle"></i>
                        
                        </span> &nbsp;&nbsp;All Rights Reserved</p>
                </div>  
            </div>
        </div>
    </div>
</body>

</html>
				""";
		
		

		helper.setText(body,true);
		mailSender.send(message);
		
	}
}
