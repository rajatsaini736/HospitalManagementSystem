import javax.swing.*;  
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class bill_payment extends JFrame{    
    public Connection create_connection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","rajat saini","RajatsainI736@gmail.com");
            return con;
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HospitalManagement.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    public void generate_bill(String name,String mobile, int total_days){
        JFrame j;
        this.setTitle("Your bill");
        String label = "Yoy are stayed "+Integer.toString(total_days)+" days here";
        String label1 = "so your total bill is - ";
        JLabel lb = new JLabel(label);
        JLabel lb1 = new JLabel(label1);
        JButton print = new JButton("Print");
        
        lb.setFont(new Font("Arial", Font.PLAIN, 28));
        lb1.setFont(new Font("Arial", Font.PLAIN, 28));
        print.setFont(new Font("Arial", Font.PLAIN, 28));
        
        lb.setBounds(200,200,400,50);
        lb1.setBounds(200,270,400,50);
        print.setBounds(300,450,100,50);
        
        print.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new_or_bill_or_close ob = new new_or_bill_or_close();
                ob.choose_choice();
            }
        });
        
        add(lb);
        add(lb1);
        add(print);
        
        setSize(1980,1080);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        }
    
}

class Doctor extends JFrame{
    public Connection create_connection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","rajat saini","RajatsainI736@gmail.com");
            return con;
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HospitalManagement.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    public void doctor_list(String name, String mob, String age, String sex, String add, String dis) throws SQLException{
        JFrame j;
        this.setTitle("Doctor List");
        JLabel Doct_list = new JLabel("Doctor's List");
        Connection con = create_connection();
        String sql = "SELECT * FROM doctor";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<String> doct_list = new ArrayList<String>();
        ArrayList<String> doct_names_list = new ArrayList<String>();
        while(rs.next()){
            String id = rs.getString("id");
            String doct_name = rs.getString("doctor_name");
            String speciality = rs.getString("speciality");
            String temp = id+" "+doct_name+"  -  "+speciality;
            doct_names_list.add(doct_name);
            doct_list.add(temp);
        }
        JCheckBox doct_1 = new JCheckBox(doct_list.get(0));
        JCheckBox doct_2 = new JCheckBox(doct_list.get(1));
        JCheckBox doct_3 = new JCheckBox(doct_list.get(2));
        JCheckBox doct_4 = new JCheckBox(doct_list.get(3));
        JCheckBox doct_5 = new JCheckBox(doct_list.get(4));
        JButton next = new JButton("next");
        
        Doct_list.setFont(new Font("Arial",Font.PLAIN, 28));
        doct_1.setFont(new Font("Arial",Font.PLAIN, 28));
        doct_2.setFont(new Font("Arial",Font.PLAIN, 28));
        doct_3.setFont(new Font("Arial",Font.PLAIN, 28));
        doct_4.setFont(new Font("Arial", Font.PLAIN, 28));
        doct_5.setFont(new Font("Arial", Font.PLAIN, 28));
        next.setFont(new Font("Arial", Font.PLAIN, 28));       
        
        Doct_list.setBounds(900,0,300,50);
        doct_1.setBounds(100,100,500,50);
        doct_2.setBounds(100,170,500,50);
        doct_3.setBounds(100,240,500,50);
        doct_4.setBounds(100,310,500,50);
        doct_5.setBounds(100,380,500,50);
        next.setBounds(200,700,200,50);
       
        next.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
                    int count= 0;
                    String doct_names = "";
                   if(doct_1.isSelected()){
                       doct_names+=doct_names_list.get(0);
                       doct_names+=" ";
                       count++;
                   }
                   if(doct_2.isSelected()){
                       doct_names+=doct_names_list.get(1);
                       doct_names+=" ";
                       count++;
                   }
                   if(doct_3.isSelected()){
                       doct_names+=doct_names_list.get(2);
                       doct_names+=" ";
                       count++;
                   }
                   if(doct_4.isSelected()){
                       doct_names+=doct_names_list.get(3);
                       doct_names+=" ";
                       count++;
                   }
                   if(doct_5.isSelected()){
                       doct_names+=doct_names_list.get(4);
                       doct_names+=" ";
                       count++;
                   }
                   if(count>0){
                   dispose();
                   Patient check_patient_form = new Patient();
                   check_patient_form.Check_patient_form(name,mob,age,sex,add,dis,doct_names);
                    }
                   else{
                       JLabel error = new JLabel("* please select at least one doctor");
                       error.setFont(new Font("Arial", Font.PLAIN, 18));
                       error.setForeground(Color.RED);
                       error.setBounds(100,60,400,50);
                       add(error);
                        invalidate();
                        validate();
                        repaint(); 
                   }
           } 
        });
        add(Doct_list);
        add(doct_1);
        add(doct_2);
        add(doct_3);
        add(doct_4);
        add(doct_5);
        add(next);
        
        setSize(1980,1080);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
}

class Patient extends JFrame{
    public Connection create_connection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","rajat saini","RajatsainI736@gmail.com");
            return con;
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HospitalManagement.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
    public void fill_details_for_bill(){
        JFrame j;
        this.setTitle("Your Bill");
        JLabel patient_name = new JLabel("Patient Name - ");
        JLabel patient_mob = new JLabel("Patient Mobile Number - ");
        JTextField pat_name = new JTextField();
        JTextField pat_mob = new JTextField();
        JButton button = new JButton("Generate Bill");
        
        patient_name.setFont(new Font("Arial", Font.PLAIN, 28));
        patient_mob.setFont(new Font("Arial", Font.PLAIN, 28));
        pat_name.setFont(new Font("Arial", Font.PLAIN, 20));
        pat_mob.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setFont(new Font("Arial", Font.PLAIN, 28));
        
        patient_name.setBounds(200,200,400,50);
        pat_name.setBounds(200,250,400,50);
        patient_mob.setBounds(200,320,400,50);
        pat_mob.setBounds(200,370,400,50);
        button.setBounds(200,430,400,50);
        
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name = pat_name.getText().toString();
                String mob = pat_mob.getText().toString();
                
                JLabel error = new JLabel();
                boolean name_check = true;
                boolean mob_check = true;
                
                for(char c: mob.toCharArray()){
                    if (Character.isDigit(c)){}
                    else{
                        mob_check = false;
                        break;
                    }
                }
                for(char c: name.toCharArray()){
                    if (Character.isDigit(c)){
                        name_check = false;
                        break;
                    }
                }
                if(!name.isEmpty() && !mob.isEmpty() && name!=null && mob!=null && name_check==true && mob_check==true && mob.length()==10){
                    try {
                        Connection con = create_connection();
                        String sql = "SELECT patient_name,patient_mobile,total_days FROM patient_details";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        ArrayList<String> patient = new ArrayList<String>();
                        String checker = name+" "+mob;
                        int total_days=0;
                        while(rs.next()){
                            String name1 = rs.getString("patient_name");
                            String mob1 = rs.getString("patient_mobile");
                            int days = rs.getInt("total_days");
                            String details = name1+" "+mob1;
                            if (details.equals(checker)){
                                total_days=days;
                            }
                            patient.add(details);
                        }
                        if(patient.contains(checker)){
                               dispose();
                               bill_payment bill = new bill_payment();
                               bill.generate_bill(name,mob,total_days);
                        }
                        else{
                            error.setText("* Please insert correct details");
                            pat_name.setText(null);
                            pat_mob.setText(null);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else{
                    error.setText("* Please insert correct details");
                    pat_name.setText(null);
                    pat_mob.setText(null);
                }
                error.setFont(new Font("Arial", Font.PLAIN, 18));
                error.setForeground(Color.RED);
                error.setBounds(200,160,400,50);
                add(error);
                invalidate();
                validate();
                repaint();
            }
        });
        
        add(patient_name);
        add(pat_name);
        add(patient_mob);
        add(pat_mob);
        add(button);
        
        setSize(1980,1080);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    
    public void Check_patient_form(String name, String mob, String age, String sex, String add, String dis, String doct_names){
        JFrame j;
        this.setTitle("Patient Form");
        
        JLabel patient_name = new JLabel("Patient Name - ");
        JLabel patient_mob = new JLabel("Patient Mobile Number - ");
        JLabel patient_age = new JLabel("Patient Age - ");
        JLabel patient_sex = new JLabel("Patient Sex - ");
        JLabel patient_add = new JLabel("Patient Address - ");
        JLabel patient_dis = new JLabel("Disease - ");
        JLabel doct_name = new JLabel("Doctor Name - ");
        JLabel Name = new JLabel(name);
        JLabel Mob = new JLabel(mob);
        JLabel Age = new JLabel(age);
        JLabel Sex = new JLabel(sex);
        JLabel Add = new JLabel(add);
        JLabel Dis = new JLabel(dis);
        JLabel Doct_name = new JLabel(doct_names);
        JButton submit = new JButton("Submit");
        
        patient_name.setFont(new Font("Arial", Font.PLAIN, 28));
        patient_mob.setFont(new Font("Arial", Font.PLAIN, 28));
        patient_age.setFont(new Font("Arial", Font.PLAIN, 28));
        patient_sex.setFont(new Font("Arail", Font.PLAIN, 28));
        patient_add.setFont(new Font("Arial", Font.PLAIN, 28));
        patient_dis.setFont(new Font("Arial", Font.PLAIN, 28));
        doct_name.setFont(new Font("Arial", Font.PLAIN, 28));
        Name.setFont(new Font("Arial", Font.PLAIN, 28));
        Mob.setFont(new Font("Arial", Font.PLAIN, 28));
        Age.setFont(new Font("Arial", Font.PLAIN, 28));
        Sex.setFont(new Font("Arial", Font.PLAIN, 28));
        Add.setFont(new Font("Arial", Font.PLAIN, 28));
        Dis.setFont(new Font("Arial", Font.PLAIN, 28));
        Doct_name.setFont(new Font("Arial", Font.PLAIN, 28));
        submit.setFont(new Font("Arial", Font.PLAIN, 28));
        
        patient_name.setBounds(200,100,400,50);
        Name.setBounds(450,100,400,50);
        patient_mob.setBounds(200,220,400,50);
        Mob.setBounds(550,220,400,50);
        patient_age.setBounds(200,350,400,50);
        Age.setBounds(450,350,100,30);
        patient_sex.setBounds(200,420,400,50);
        Sex.setBounds(450,420,100,30);
        patient_add.setBounds(200,480,400,50);
        Add.setBounds(450,480,500,50);
        patient_dis.setBounds(200,600,400,50);
        Dis.setBounds(450,600,500,50);
        doct_name.setBounds(200,710,500,50);
        Doct_name.setBounds(450,710,500,50);
        submit.setBounds(300,800,200,50);
        
        add(patient_name);
        add(patient_mob);
        add(patient_age);
        add(patient_sex);
        add(patient_add);
        add(patient_dis);
        add(doct_name);
        add(Name);
        add(Mob);
        add(Age);
        add(Sex);
        add(Add);
        add(Dis);
        add(Doct_name);
        add(submit);
        
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                Connection con = create_connection();
                String sql = "SELECT * FROM patient_details";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ArrayList<String> patient = new ArrayList<String>();
                while(rs.next()){
                    String name = rs.getString("patient_name");
                    String mob = rs.getString("patient_mobile");
                    String details = name+" "+mob;
                    patient.add(details);
                }
                String checker = name+" "+mob;
                if (patient.contains(checker)){
                       Connection con1 = create_connection();
                       String sql1 = "SELECT total_days from patient_details where patient_mobile = ?";
                       PreparedStatement pstmt = con1.prepareStatement(sql1);
                       pstmt.setString(1,mob);
                       ResultSet rs1 = pstmt.executeQuery();
                       int total = 0;
                       while(rs1.next()){
                           total = rs1.getInt("total_days");
                       }
                       
                       Connection con2 = create_connection();
                       String sql2 = "UPDATE patient_details set total_days = ? where patient_mobile = ?";
                       PreparedStatement pstmt1 = con2.prepareStatement(sql2);
                       pstmt1.setInt(1,total+1);
                       pstmt1.setString(2,mob);
                       pstmt1.executeUpdate();
                   }
                else{
                     Connection con1 = create_connection();
                     String sql1 = "INSERT INTO patient_details(patient_name,patient_mobile,patient_age,patient_sex,patient_address,patient_disease,doctor_name,total_days) VALUES(?,?,?,?,?,?,?,?)";
                     PreparedStatement pstmt = con1.prepareStatement(sql1);
                     pstmt.setString(1,name);
                     pstmt.setString(2,mob);
                     pstmt.setString(3,age);
                     pstmt.setString(4,sex);
                     pstmt.setString(5,add);
                     pstmt.setString(6,dis);
                     pstmt.setString(7,doct_names);
                     pstmt.setInt(8,1);
                     pstmt.executeUpdate();
                 }
                } catch (SQLException ex) {
                   Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
              }


                dispose();
                new_or_bill_or_close obj = new new_or_bill_or_close();
                obj.choose_choice();
           }
            });
        
        setSize(1980,1080);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    public void fill_patient_details(){
        JFrame j;
        this.setTitle("Patient Form");

        //CREATING LABELS, TEXTFIELDS AND BUTTONS
        JLabel patient_name = new JLabel("Patient Name - ");
        JLabel patient_mob = new JLabel("Patient Mobile Number - ");
        JLabel patient_age = new JLabel("Patient Age - ");
        JLabel patient_sex = new JLabel("Patient Sex - ");
        JLabel patient_add = new JLabel("Patient Address - ");
        JLabel patient_disease = new JLabel("Disease - ");
        JButton next = new JButton("Next");

        String[] age = new String[101];
        for (int i = 0;i<=100;i++){
            age[i] = Integer.toString(i);
        }
        String sex[] = {"Male","Female","Other"};
        
        JTextField pat_name = new JTextField();
        JTextField pat_mob = new JTextField();
        JComboBox pat_age = new JComboBox(age);
        JComboBox pat_sex = new JComboBox(sex);
        JTextField pat_add = new JTextField();
        JTextField pat_dis = new JTextField();

        //SET SIZE AND FONT OF THE LABLES
        patient_name.setFont(new Font("Arial", Font.PLAIN, 28));
        patient_mob.setFont(new Font("Arial", Font.PLAIN, 28));
        patient_age.setFont(new Font("Arial", Font.PLAIN, 28));
        patient_sex.setFont(new Font("Arial", Font.PLAIN, 28));
        patient_add.setFont(new Font("Arial", Font.PLAIN, 28));
        patient_disease.setFont(new Font("Arial", Font.PLAIN, 28));
        pat_name.setFont(new Font("Arial", Font.PLAIN, 20));
        pat_mob.setFont(new Font("Arial", Font.PLAIN, 20));
        pat_age.setFont(new Font("Arial", Font.PLAIN, 20));
        pat_sex.setFont(new Font("Arial", Font.PLAIN, 20));
        pat_add.setFont(new Font("Arial", Font.PLAIN, 20));
        pat_dis.setFont(new Font("Arial", Font.PLAIN, 20));
        next.setFont(new Font("Arial", Font.PLAIN, 28));

        //PLACING LABELS AND FIELDS ON WINDOW
        patient_name.setBounds(200,200,400,50);
        pat_name.setBounds(200,250,400,50);
        patient_mob.setBounds(200,320,400,50);
        pat_mob.setBounds(200,370,400,50);
        patient_age.setBounds(200,450,400,50);
        pat_age.setBounds(400,460,100,30);
        patient_sex.setBounds(200,520,400,50);
        pat_sex.setBounds(400,530,100,30);
        patient_add.setBounds(200,580,400,50);
        pat_add.setBounds(200,630,500,50);
        patient_disease.setBounds(200,700,400,50);
        pat_dis.setBounds(200,760,500,50);
        next.setBounds(350,840,180,40);

        //ACTION AFTER CLICK ON NEXT BUTTON
        next.addActionListener(new ActionListener(){            
            public void actionPerformed(ActionEvent e){
                String name = pat_name.getText().toString();
                String mob = pat_mob.getText().toString();
                String age = pat_age.getSelectedItem().toString();
                String sex = pat_sex.getSelectedItem().toString();
                String add = pat_add.getText().toString();
                String dis = pat_dis.getText().toString();

                JLabel error = new JLabel();

                boolean name_check = true;
                boolean mob_check = true;
                // boolean add_check = true;
                boolean dis_check = true;

                // CHECKING IF THERE IS ANY DIGIT IN NAME
                for (char c : name.toCharArray()){
                    if (Character.isDigit(c)){
                        name_check= false;
                        break;
                    }
                }
                // CHECKING IF THERE IS ANY CHARCATER IN MOBILE NO. AND LENGTH OF MOB ALSO
                for (char c: mob.toCharArray()){
                    if (Character.isDigit(c)){}
                    else{
                        mob_check=false;
                        break;
                    }
                }
                // CHECKING IF THERE IS ANY DIGIT IN DISEASE
                for (char c: dis.toCharArray()){
                    if(Character.isDigit(c)){
                        dis_check=false;
                        break;
                    }
                }
                if (!name.isEmpty()  && !mob.isEmpty() && !age.isEmpty() && !sex.isEmpty() && !add.isEmpty() && !dis.isEmpty() && name!=null && mob!=null && age!=null && sex!=null && add!=null && dis!=null && name_check==true && mob_check==true && dis_check==true && mob.length()==10){

                    
                        dispose();
                        Doctor doctor = new Doctor();
                    try {
                        doctor.doctor_list(name,mob,age,sex,add,dis);
                    } catch (SQLException ex) {
                        Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                        error.setText("* Please insert correct details");
                        pat_name.setText(null);
                        pat_mob.setText(null);
                        pat_age.setSelectedItem("0");
                        pat_sex.setSelectedItem("Male");
                        pat_add.setText(null);
                        pat_dis.setText(null);
                }
                error.setFont(new Font("Arial", Font.PLAIN, 18));
                error.setForeground(Color.RED);
                error.setBounds(200,160,400,50);
                add(error);

                invalidate();
                validate();
                repaint(); 

            }
        });

        // ADDING LABELS AND FIELDS ON WINDOW
        add(patient_name);
        add(patient_mob);
        add(patient_age);
        add(patient_sex);
        add(patient_add);
        add(pat_name);
        add(pat_mob);
        add(pat_age);
        add(pat_sex);
        add(pat_add);
        add(patient_disease);
        add(pat_dis);
        add(next);
  
        setSize(1980,1080);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(153,230,255));
        g.fillRect(800,0,1980,1080);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("D:\\Aashish\\Java\\download4.png");
        g.drawImage(i,1200,200,this);
    }
}

class new_or_bill_or_close extends JFrame{
    public void choose_choice(){
        JFrame j;
        this.setTitle("New Patient or Bill");
        JButton new_patient = new JButton("New Patient");
        JButton bill = new JButton("Bill or Payment");
        JButton exit = new JButton("Exit");
        
        new_patient.setFont(new Font("Arial", Font.PLAIN, 20));
        bill.setFont(new Font("Arial", Font.PLAIN, 20));
        exit.setFont(new Font("Arial", Font.PLAIN, 20));
        
        new_patient.setBounds(150,20,300,50);
        bill.setBounds(150,90,300,50);
        exit.setBounds(150,160,300,50);
        
        new_patient.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                Patient pat = new Patient();
                pat.fill_patient_details();
            }
        });
        
        bill.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();     
                Patient pat = new Patient();
                pat.fill_details_for_bill();
            }
        });    
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // TO CLOSE ALL THE WINDOW
                Window[] windows = getWindows();
                for (Window window : windows){
                if (window instanceof JFrame){
                window.dispose();
             }
         }
            }
        });
              
        add(new_patient);
        add(bill);
        add(exit);
        
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(600,280);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
}

class Login extends JFrame{
    public void Login_window(){
        JFrame j;
        this.setTitle("Log-in Form");
        // CREATING LABELS,TEXTFIELDS, BUTTONS
        JLabel receptionist_id = new JLabel("Receptionist ID - ");
        JLabel receptionist_passwd = new JLabel("Receptionist Password - ");
        JTextField recept_id = new JTextField();
        JPasswordField recept_passwd = new JPasswordField();
        JButton login = new JButton("Log-in");

        // SET SIZE AND FONT OF THE TEXT
        receptionist_id.setFont(new Font("Arial", Font.PLAIN, 20));
        receptionist_passwd.setFont(new Font("Arial", Font.PLAIN, 20));
        recept_id.setFont(new Font("Arial", Font.PLAIN, 20));
        recept_passwd.setFont(new Font("Arial", Font.PLAIN, 20));
        login.setFont(new Font("Arial", Font.PLAIN, 20));

        // PLACING MANNUALLY ON WINDOW
        receptionist_id.setBounds(300,150,300,40);
        receptionist_passwd.setBounds(300,260,300,40);
        recept_id.setBounds(300,200,250,40);
        recept_passwd.setBounds(300,310,250,40);
        login.setBounds(340,390,150,40);

        // ACTION AFTER CLICK ON BUTTON
        login.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        if( recept_id.getText().equals("123") && recept_passwd.getText().equals("123")){
            dispose();
            new_or_bill_or_close obj = new new_or_bill_or_close();
            obj.choose_choice();
             }
        else{
                // PRINT ERROR IF RECEPTIONIST IS INSERT WRONG DETAILS
                JLabel error = new JLabel("* Invalid Receptionist ID or Receptionist Password");
                error.setFont(new Font("Arial", Font.PLAIN, 12));
                error.setForeground(Color.RED);
                error.setBounds(300,110,600,50);
                recept_id.setText(null);
                recept_passwd.setText(null);
                add(error);
                invalidate();
                validate();
                repaint();
            }
        }
            });

        // ADDING THEM ON WINDOW
        add(receptionist_id);
        add(receptionist_passwd);
        add(recept_id);
        add(recept_passwd);
        add(login);

        setSize(600,600);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    // DRAW HALF RECTANGLE
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(128,128,255));
        g.fillRect(0,0,290,600);

        // READING IMAGE AND PLACE ON HALF RECTANGLE
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("D:\\Aashish\\Java\\download.png");
        g.drawImage(i,100,200,this);
    }
} 

public class HospitalManagement{

    public static void main(String[] args) {
       JFrame frame = buildFrame();
       Login login = new Login();
       login.Login_window();

    }
    public static JFrame buildFrame(){
        JFrame frame = new JFrame();
        frame.setSize(1980,1080);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        return frame;
    }
    
}
