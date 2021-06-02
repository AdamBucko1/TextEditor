package sk.stuba.fei.uim.oop.utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NasMalyEditor extends JFrame implements ActionListener {
    JMenuItem exitItem;
    JMenuItem openFile;
    JMenuItem saveAsFile;
    TextArea edit;

    public NasMalyEditor() {
        super("Moj Editor");
        edit = new TextArea();
        add(edit);

        JMenuBar bar=new JMenuBar();
        setJMenuBar(bar);

        JMenu file=new JMenu("File");

        exitItem=new JMenuItem("Exit");
        exitItem.addActionListener(this);

        file.add(exitItem);

        openFile=new JMenuItem("Open");
        openFile.addActionListener(this);

        file.add(openFile);

        saveAsFile=new JMenuItem("Save");
        saveAsFile.addActionListener(this);

        file.add(saveAsFile);
        bar.add(file);
        setSize(500,500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==exitItem){
            System.exit(0);
        }
        if (e.getSource()==openFile){
            //nacitaj subor
            FileDialog openDialog=new FileDialog(this,"Otvor",FileDialog.LOAD);
            openDialog.setVisible(true);
            if (openDialog.getFile()!=null){
                try {
                    FileInputStream fis=new FileInputStream(openDialog.getDirectory()+openDialog.getFile());
                    InputStreamReader in= new InputStreamReader(fis);
                    BufferedReader b=new BufferedReader(in);
                    String obsahSuboru="";
                    while (true){
                        String nacitane=b.readLine();
                        if (nacitane==null){
                            break;
                        }
                        obsahSuboru=obsahSuboru+nacitane+"\n";
                    }
                    edit.setText(obsahSuboru);
                    b.close();
                }
                catch (IOException exc){
                    System.out.println(exc);
                }
            }
        }
        if (e.getSource()==saveAsFile){
            //savni subor
            FileDialog saveDialog= new FileDialog(this,"Uloz ako",FileDialog.SAVE);
            saveDialog.setVisible(true);
            if (saveDialog.getFile()!=null){
                try {
                    FileOutputStream fos= new FileOutputStream(saveDialog.getDirectory()+saveDialog.getFile());
                    OutputStreamWriter out=new OutputStreamWriter(fos);
                    PrintWriter p=new PrintWriter(out);
                    p.println(edit.getText());
                    p.close();
                }
                catch (FileNotFoundException exc){
                    System.out.println("Neveim ulozit");
                }
            }
        }
    }
}
