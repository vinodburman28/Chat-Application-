import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;


public class Server extends JFrame implements ActionListener {

    JTextField text;      // global declaration of text field
    JPanel a1;
   static Box vertical = Box.createVerticalBox(); // layout and vertical box help to align the messages in vertical
   static JFrame f=new JFrame(); // create an static object of frame to perform static operations in main class
   static DataOutputStream dout; // global declaration of dout
    //Constructor
    Server()
    {
        // heading work begins here-

       f.setLayout(null); // heading layout

        JPanel p1=new JPanel(); //create layout
        p1.setBackground(new Color(7,94,84)); // set the color of heading green
        p1.setBounds(0,0,450,50);  //set the size of layout
        p1.setLayout(null);
        f. add(p1); // add layout in frame

        // adding back icon in header layout
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/3 (1).png"));   // loading image
        Image i2=i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);    //  set the image size and make it default here
        ImageIcon i3=new ImageIcon(i2);
        JLabel back=new JLabel(i3);
        back.setBounds(5,15,25,25);
        p1.add(back);                                                        // add image in the heading layout

        //mouse input in back icon
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                System.exit(0);                      // close the program when mouse is clicked in back icon
            }
        });

        // adding profile picture icon in header layout

        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icon/shinchan.png"));   // profile image
        Image i5=i4.getImage().getScaledInstance(45,35,Image.SCALE_DEFAULT);    //  set the image size and make it default here
        ImageIcon i6=new ImageIcon(i5);
        JLabel profile =new JLabel(i6);
        profile.setBounds(40,7,45,35);
        p1.add(profile);                                                        // add profile image in the heading layout

        // adding video icon in header layout

        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icon/video.png"));   // video image
        Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);    //  set the image size and make it default here
        ImageIcon i9=new ImageIcon(i8);
        JLabel video =new JLabel(i9);
        video.setBounds(338,10,30,30);
        p1.add(video);                                                        // add video image in the heading layout

        // adding phone icon in header layout

        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icon/phone.png"));   // phone image
        Image i11=i10.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);    //  set the image size and make it default here
        ImageIcon i12=new ImageIcon(i11);
        JLabel phone =new JLabel(i12);
        phone.setBounds(378,10,30,30);
        p1.add(phone);                                                        // add phone image in the heading layout



        // adding 3 dot icon in header layout

        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icon/3dot.png"));   // 3 dot image
        Image i14=i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);    //  set the image size and make it default here
        ImageIcon i15=new ImageIcon(i14);
        JLabel more =new JLabel(i15);
        more.setBounds(425,13,10,25);
        p1.add(more);                                          // add 3 dot image in the heading layout

        // adding name of server
        JLabel name = new JLabel("Shinchan");
        name.setBounds(105,10,100,15);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        name.setForeground(Color.WHITE);
        p1.add(name);

        // adding active now
        JLabel status= new JLabel("Active Now");
        status.setBounds(108,30,100,12);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,10));
        status.setForeground(Color.WHITE);
        p1.add(status);

        // creating another panel for messages
         a1 = new JPanel();
        a1.setBounds(5,55,440,600);
        f.add(a1);

        // creating text field
       text = new JTextField();
        text.setBounds(5,655,390,45);
        text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(text);

        // creating send button
        JButton send= new JButton("Send");
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,13));
        send.setBounds(392,655,65,45);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        f.add(send);






        // frame works begin here
        f. setSize(450,700);                                        //size of chat frame
        f. setLocation(60,10);
        f. setUndecorated(true);//set the location of frame
        f. getContentPane().setBackground(Color.WHITE);                        // set the background color of frame
        f. setVisible(true);                                                   // for the frame visibility
    }
    public void actionPerformed(ActionEvent ae)          // overiding the abstract class of Action listener
    {
        // process of transferring messages from text field to panel by click to send button
        try {
            String out = text.getText();    // here the msg from text field is stored in out variable

            JPanel p2 = formatLabel(out);    // calling formatLabel function

            a1.setLayout(new BorderLayout());           // align the messages in right side of the panel
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);           // it put the messages at the end of the line /right side
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15)); // it manages the gap between two messages

            a1.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);

            // empty the text field after sending message
            text.setText("");

            // these funtions reload the page
            repaint();
            invalidate();
            validate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // creating a function for message box
    public static JPanel formatLabel(String out){
        JPanel panel =new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output=new JLabel("<html><p style=\"width: 150px\">"+out+"</p></html>"); //using html properties in java to fixed the size of the box
        output.setFont(new Font("Tahoma",Font.PLAIN,16));          //set the font style of message
        output.setBackground(new Color(128,111,111));                 //set the background color to green of message
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));  //set the border of the message

        panel.add(output);

        //adding the time of the message

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));  // set text function is used for current time function calculation
        panel.add(time);
        return  panel;
    }

    public static void main(String[] args){
        new Server();

        // server work begins here

       try {
           ServerSocket skt = new ServerSocket(6001); // create a server
           while (true) {
               Socket s = skt.accept();     // accept the message from client
               DataInputStream din = new DataInputStream(s.getInputStream());   // for receiving the messasge
                dout = new DataOutputStream((s.getOutputStream())); // for sending the message

               while(true){
                   String msg= din.readUTF();   // it encoded the client msg
                   JPanel panel=formatLabel(msg); // it will format the message

                   JPanel left = new JPanel(new BorderLayout());
                   left.add(panel, BorderLayout.LINE_START);
                   vertical.add (left);
                   f.validate();



               }
           }
           } catch(Exception e){
               e.printStackTrace();
           }
    }


}