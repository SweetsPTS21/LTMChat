/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templateswwwwwwwwwwwwwwwwwwwwwwwwwwww
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author boixi
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import controller.DataFile;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import controller.Decode;
import controller.Encode;
import controller.Tags;

public class ChatJFrame extends javax.swing.JFrame {
    // Socket
    private static String URL_DIR = System.getProperty("user.dir");
    private Socket socketChat;
    private String nameUser = "", nameGuest = "", nameFile = "", sentPath = "", receivedPath="";
    public boolean isStop = false, isSendFile = false, isReceiveFile = false;
    private ChatJFrame.ChatRoom chat;
    private int portServer = 0;
    private static final long serialVersionUID = 1L;
    private ChatJFrame frame = this;
        
    public ChatJFrame(String user, String guest, Socket socket, int port) throws Exception {
        nameUser = user;
        nameGuest = guest;
        socketChat = socket;
        frame = new ChatJFrame(user, guest, socket, port, port);
        frame.setVisible(true);
    }
    
    public ChatJFrame(String user, String guest, Socket socket, int port, int a) throws Exception {
        // TODO Auto-generated constructor stub
	initComponents();
        setSize(600, 600);
        setLocationRelativeTo(this);
        nameUser = user;
        nameGuest = guest;
        socketChat = socket;
        portServer = port;
        System.out.println("user: " + user);
        System.out.println("Guest: " + guest);
        System.out.println("Port: " + port);
        System.out.println("Socket: " + socket);
        chat = new ChatJFrame.ChatRoom(socketChat, nameUser, nameGuest);
        
        lblGuestName.setText(nameGuest);
        chat.start();	
    }
    
    public void updateChat_receive(String msg) {
        appendToPane(txtDisplayMessage, 
//                "<div class='left' style='width: 40%; background-color: #f1f0f0;'>" + "    "
//                + msg + "<br>" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + "</div>");
                "<table class='left' style='color: black; clear:both; width: 100%;'>" + "<tr align='left'>"
                        + "<td style='width: 40%; background-color: #f1f0f0; border: 1px solid #f1f0f0; border-radius: 4px;'>" 
                        + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + "<br>" + msg
                        + "<td style='width: 59%; '></td>"                      
                        + "</td> </tr>" + "</table>");
    }
    
    public void updateChat_send(String msg) {
        appendToPane(txtDisplayMessage,
                "<table class='bang' style='color: white; clear:both; width: 100%;'>" + "<tr align='right'>"
                        + "<td style='width: 59%; '></td>" + "<td style='width: 40%; background-color: #0084ff; border: 1px solid #0084ff; border-radius: 4px;'>"
                        + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + "<br>" + msg
                        + "</td> </tr>" + "</table>");
    }
    
    public void updateChat_notify(String msg) {
        appendToPane(txtDisplayMessage,
                "<table class='bang' style='color: white; clear:both; width: 100%;'>" 
                        + "<tr align='right'>"
                            + "<td style='width: 59%; '></td>" 
                            + "<td style='width: 40%; background-color: #f1c40f;'>" 
                            + msg
                        + "</td> </tr>" + "</table>");
    }
    public void updateChat_receiveImg(String img) {
        appendToPane(txtDisplayMessage,
                "<table class='bang' style='color: white; clear:both; width: 100%;'>" 
                        + "<tr align='right'>"
                            + "<td style='width: 59%; '></td>" 
                            + "<td style='width: 40%; background-color: #f1c40f;'>" 
                            + img
                        + "</td> </tr>" + "</table>");
    }

    public void updateChat_send_Symbol(String msg) {
        appendToPane(txtDisplayMessage, "<table style='width: 100%;'>" + "<tr align='right'>"
                + "<td style='width: 59%;'></td>" + "<td style='width: 40%;'>" + msg + "</td> </tr>" + "</table>");
    }
    
    private void appendToPane(JTextPane tp, String msg) {
        HTMLDocument doc = (HTMLDocument) tp.getDocument();
        HTMLEditorKit editorKit = (HTMLEditorKit) tp.getEditorKit();
        try {
            editorKit.insertHTML(doc, doc.getLength(), msg, 0, 0, null);
            tp.setCaretPosition(doc.getLength());
        } catch (IOException | BadLocationException e) {
            System.err.println(e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        txtMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDisplayMessage = new javax.swing.JTextPane();
        btnSendFile = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblGuestName = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblReceive = new javax.swing.JLabel();

        jFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooserActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Team 5 Messenger");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtMessage.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMessageActionPerformed(evt);
            }
        });
        txtMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMessageKeyPressed(evt);
            }
        });

        btnSend.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtDisplayMessage.setEditable(false);
        txtDisplayMessage.setContentType("text/html"); // NOI18N
        txtDisplayMessage.setText("<div class='clear' style='background-color:white'></div>");
        jScrollPane1.setViewportView(txtDisplayMessage);

        btnSendFile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSendFile.setText("File");
        btnSendFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendFileActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile.png"))); // NOI18N
        jLabel1.setText("Avatar");

        lblGuestName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGuestName.setText("Name");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Video");

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Voice");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGuestName, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGuestName, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblReceive.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSendFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblReceive, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMessage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSendFile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblReceive, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        String msg = txtMessage.getText();
	// Clear messageif
	if (msg.equals(""))
            return;
            txtMessage.setText("");
	try {
            chat.sendMessage(Encode.sendMessage(msg));
	} catch (Exception e1) {
            e1.printStackTrace();
	}
	updateChat_send(msg);
    }//GEN-LAST:event_btnSendActionPerformed

    private void txtMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMessageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMessageActionPerformed

    private void txtMessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMessageKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSend.doClick();
	}
    }//GEN-LAST:event_txtMessageKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        try {
            isStop = true;
            frame.dispose();
            chat.sendMessage(Tags.CHAT_CLOSE_TAG);
            chat.stopChat();
            System.gc();
	} catch (Exception e) {
            e.printStackTrace();
	}
    }//GEN-LAST:event_formWindowClosing

    private void btnSendFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendFileActionPerformed
        // TODO add your handling code here:
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	int result = jFileChooser.showOpenDialog(frame);
	if (result == JFileChooser.APPROVE_OPTION) {
            isSendFile = true;
            String path_send = (jFileChooser.getSelectedFile().getAbsolutePath());
            sentPath = path_send;
            System.out.println("pathFile: " + path_send);
            nameFile = jFileChooser.getSelectedFile().getName();
            File file = jFileChooser.getSelectedFile();
            // if (isSendFile)
            try {
                chat.sendMessage(Encode.sendFile(nameFile));
                if(nameFile.contains(".jpg")||nameFile.contains(".png")) {
                    Path src = Paths.get(path_send);
                    Path target = Paths.get(URL_DIR + "/build/classes/imageSent").resolve(nameFile);
                    Files.copy(src, target, REPLACE_EXISTING);
                }
            } catch (Exception ex) {
                System.err.println(ex);
            }
            System.out.println("nameFile: " + nameFile);
            try {
                chat.sendFile(file);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                System.err.println(e1);
            }
	}
    }//GEN-LAST:event_btnSendFileActionPerformed

    private void jFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooserActionPerformed
        // TODO add your handling code here:
        int result = jFileChooser.showOpenDialog(frame);
	if (result == JFileChooser.APPROVE_OPTION) {
            isSendFile = true;          
            nameFile = jFileChooser.getSelectedFile().getName();
            File file = jFileChooser.getSelectedFile();
            // if (isSendFile)
            try {
                chat.sendMessage(Encode.sendFile(nameFile));              
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("nameFile: " + nameFile);
            try {
                chat.sendFile(file);              
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }//GEN-LAST:event_jFileChooserActionPerformed


public class ChatRoom extends Thread {
    private Socket connect;
    private ObjectOutputStream outPeer;
    private ObjectInputStream inPeer;
    private boolean continueSendFile = true, finishReceive = false;
    private int sizeOfSend = 0, sizeOfData = 0, sizeFile = 0, sizeReceive = 0;
    private String nameFileReceive = "";
    private InputStream inFileSend;
    private DataFile dataFile;
	
    public ChatRoom(Socket connection, String name, String guest) throws Exception {
            connect = new Socket();
            connect = connection;
            nameGuest = guest;
            System.out.println(connect);
	}

    @Override
    public void run() {
            super.run();
            System.out.println("Chat Room start");
            OutputStream out = null;
            
            while (!isStop) {
		try {
                    inPeer = new ObjectInputStream(connect.getInputStream());
                    Object obj = inPeer.readObject();
                    if (obj instanceof String) {
                        String msgObj = obj.toString();
                        if (msgObj.equals(Tags.CHAT_CLOSE_TAG)) {
                            isStop = true;
                            Tags.show(frame, nameGuest + " closed chat with you! This windows will also be closed.",false);
                            try {
                                isStop = true;
                                frame.dispose();
                                chat.sendMessage(Tags.CHAT_CLOSE_TAG);
                                chat.stopChat();
                                System.gc();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            connect.close();
                            break;
			}
                        if (Decode.checkFile(msgObj)) {                          
                            isReceiveFile = true;
                            nameFileReceive = msgObj.substring(10, msgObj.length() - 11);
                            System.out.println("Check file: " + URL_DIR + "/" + nameFileReceive);
                            File fileReceive = new File(URL_DIR + "/" + nameFileReceive);
                            if (!fileReceive.exists()) {
                                fileReceive.createNewFile();
                            }
                            String msg = Tags.FILE_REQ_ACK_OPEN_TAG + Integer.toBinaryString(portServer)
                                    + Tags.FILE_REQ_ACK_CLOSE_TAG;
                            sendMessage(msg);
                            
                        } else if (Decode.checkFeedBack(msgObj)) {
                            btnSendFile.setEnabled(false);
                            
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        sendMessage(Tags.FILE_DATA_BEGIN_TAG);
                                        updateChat_notify("You are sending file: " + nameFile);
                                        isSendFile = false;
//					sendFile(txtMessage.getText());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        } else if (msgObj.equals(Tags.FILE_DATA_BEGIN_TAG)) {
                            lblReceive.setVisible(true);
                            out = new FileOutputStream(URL_DIR + nameFileReceive);
                        } else if (msgObj.equals(Tags.FILE_DATA_CLOSE_TAG)) {
                            System.out.println("Close file: " + URL_DIR + "\\" + nameFileReceive);                            
                            updateChat_receive("You receive file: " + nameFileReceive + " with size " + sizeReceive + " KB");
                            sizeReceive = 0;
                            out.flush();
                            out.close();
                            lblReceive.setVisible(false);
                            System.out.println("Choose save file path");                           
                            new Thread(new Runnable() {                               
                                @Override
                                public void run() {
                                    System.out.println("Choose save file path");
                                    try {
                                        showSaveFile();
                                    } catch (Exception ex) {
                                        Logger.getLogger(ChatJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    updateChat_receive("Save Path: " + receivedPath + "\\" + nameFileReceive);
                                }
                            }).start();
                            finishReceive = true;                         
                        } else {
                            String message = Decode.getMessage(msgObj);
                            updateChat_receive(message);
                        }
                    } else if (obj instanceof DataFile) {
                        DataFile data = (DataFile) obj;
                        ++sizeReceive;
                        out.write(data.data);
                    }
                } catch (Exception e) {
                    System.err.println(e);
                    File fileTemp = new File(URL_DIR + nameFileReceive);
                    if (fileTemp.exists() && !finishReceive) {
                        fileTemp.delete();
                    }
		}
            }
    }
    
    // void send Message
    public synchronized void sendMessage(Object obj) throws Exception {
	outPeer = new ObjectOutputStream(connect.getOutputStream());
	// only send text
	if (obj instanceof String) {
            String message = obj.toString();
            outPeer.writeObject(message);
            outPeer.flush();
            if (isReceiveFile)
                isReceiveFile = false;                              
            }
            // send attach file
            else if (obj instanceof DataFile) {
                outPeer.writeObject(obj);
                outPeer.flush();
            }
	}
    private void getData(File file) throws Exception {
        File fileData = file;
        if (fileData.exists()) {
            sizeOfSend = 0;
            dataFile = new DataFile();
            sizeFile = (int) fileData.length();
            sizeOfData = sizeFile % 1024 == 0 ? (int) (fileData.length() / 1024)
                    : (int) (fileData.length() / 1024) + 1;
            inFileSend = new FileInputStream(fileData);
        }
    }
    
    public void sendFile(File file) throws Exception {
        btnSendFile.setEnabled(false);
        getData(file);
        lblReceive.setVisible(true);
        if (sizeOfData > Tags.MAX_MSG_SIZE / 1024) {
            lblReceive.setText("File is too large(max 1024kb)");
            inFileSend.close();
            sendMessage(Tags.FILE_DATA_CLOSE_TAG);
            btnSendFile.setEnabled(true);
            isSendFile = false;
            inFileSend.close();
            return;
        }
        
        lblReceive.setText("Sending ...");
        do {
            System.out.println("sizeOfSend : " + sizeOfSend);
            if (continueSendFile) {
                continueSendFile = false;
                new Thread(new Runnable() {      
                    @Override						
                    public void run() {
                        try {
                            inFileSend.read(dataFile.data);
                            sendMessage(dataFile);
                            sizeOfSend++;
                            if (sizeOfSend == sizeOfData - 1) {
                                int size = sizeFile - sizeOfSend * 1024;
                                dataFile = new DataFile(size);
                            }
//                            progressBar.setValue(sizeOfSend * 100 / sizeOfData);
                            if (sizeOfSend >= sizeOfData) {
                                inFileSend.close();
                                isSendFile = true;
                                sendMessage(Tags.FILE_DATA_CLOSE_TAG);
//                                progressBar.setVisible(false);
                                lblReceive.setVisible(false);
                                isSendFile = false;
                                btnSendFile.setEnabled(true);
                                updateChat_notify("File sent complete");
                                if(nameFile.contains(".jpg")||nameFile.contains(".png")) {
                                    updateChat_notify("Path: " + sentPath);
                                    String imgSent = "<img src='" 
                                            + ChatJFrame.class.getResource("/imageSent/" + nameFile) 
                                            + "' width = '400' height='400'></img>";                                   
                                    updateChat_receiveImg(imgSent);
                                }
                                inFileSend.close();
                            }
                            continueSendFile = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }			
        } while (sizeOfSend < sizeOfData);
    }

    private void showSaveFile() throws Exception {
        System.out.println("Choose save file path");
        while (true) {
            jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = jFileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath() + "/" + nameFileReceive);
                receivedPath = jFileChooser.getSelectedFile().getAbsolutePath();
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                        Thread.sleep(1000);
                        InputStream input = new FileInputStream(URL_DIR + nameFileReceive);
                        OutputStream output = new FileOutputStream(file.getAbsolutePath());
                        copyFileReceive(input, output, URL_DIR + nameFileReceive);
                        
                        Path src = Paths.get(receivedPath + "/" + nameFileReceive);
                        Path target = Paths.get(URL_DIR + "/build/classes/imageReceived").resolve(nameFileReceive);
                        try {
                            Files.copy(src, target, REPLACE_EXISTING);
                        } catch (IOException ex) {                                        
                            Logger.getLogger(ChatJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                
                        }
                        String imgReceived = "<img src='" 
                                + ChatJFrame.class.getResource("/imageReceived/" + nameFileReceive) 
                                + "' width = '400' height='400'></img>";
                        updateChat_receive(imgReceived);
                    } catch (IOException | InterruptedException e) {
                        System.err.println(e);
                        Tags.show(frame, "Your file receive has error!!!", false);
                    }
                    break;
                } else {
                    int resultContinue = Tags.show(frame, "File is exists. Please choose other location!", true);
                    if (resultContinue != 0) {
                        break;
                    }
                 
                }
            }
        }
    }
    
    public void stopChat() {
	try {
            connect.close();
	} catch (IOException e) {
            e.printStackTrace();
	}
    }
    public void copyFileReceive(InputStream inputStr, OutputStream outputStr, String path) throws IOException {
        byte[] buffer = new byte[1024];
        int lenght;
        while ((lenght = inputStr.read(buffer)) > 0) {
            outputStr.write(buffer, 0, lenght);
        }
        inputStr.close();
        outputStr.close();
        File fileTemp = new File(path);
        if (fileTemp.exists()) {
            fileTemp.delete();
        }
    }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnSendFile;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGuestName;
    private javax.swing.JLabel lblReceive;
    private javax.swing.JTextPane txtDisplayMessage;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}
