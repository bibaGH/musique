/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musique;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.SAVE_DIALOG;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nabila
 */
public class AccueilMusique extends javax.swing.JFrame {
    
    private static String csvFile;
    private static String LastcsvFile = "";
    private DefaultTableModel idtm;
    private static String[] tLigne;
    private static String[] tabMusique;
    private static ArrayList<String[]> maListe = new ArrayList<String[]>();
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

    /**
     * Creates new form AccueilMusique
     */
    public AccueilMusique() {
        initComponents();
        Connexion();
        remplirComboGenre();
        build();//On initialise notre fenêtre

    }
    
    private void build() {
        setTitle("Fenetre principale"); //On donne un titre à l'application
        setSize(600, 600); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
    }
    
    public void remplirComboGenre() {
        String pilote = "com.mysql.jdbc.Driver";
        ArrayList<String> ListGenre = new ArrayList<String>();
        try {
            Class.forName(pilote);
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/musiques", "root", "");
            java.sql.Statement instruction = cnx.createStatement();
            ResultSet resultat = instruction.executeQuery("SELECT genre.titre FROM genre");

            //To remove previously added rows
            while (resultat.next()) {
                ListGenre.add(resultat.getString(1));
            }
            Iterator iterator = ListGenre.iterator();
            while (iterator.hasNext()) {
                jComboBoxListGenre.addItem(iterator.next());
            }
            
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
// TODO Auto-generated catch block 
            e1.printStackTrace();
        }
        
    }
    
    public void Connexion() {
        String pilote = "com.mysql.jdbc.Driver";
        try {
            Class.forName(pilote);
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/musiques", "root", "");
            java.sql.Statement instruction = cnx.createStatement();
            ResultSet resultat = instruction.executeQuery("SELECT piste.id_piste,piste.titre,piste.duree,artiste.prenom AS artiste,album.titre AS album,genre.titre AS genre FROM piste, album, genre, artiste WHERE piste.id_album=album.id_album AND album.id_genre=genre.id_genre AND album.id_artiste=artiste.id_artiste");

            //To remove previously added rows
            while (jTable1.getRowCount() > 0) {
                ((DefaultTableModel) jTable1.getModel()).removeRow(0);
            }
            int columns = resultat.getMetaData().getColumnCount();
            while (resultat.next()) {
                Object[] row = new Object[columns];//tableau de 6 cases
                for (int i = 1; i <= columns; i++) {
                    //je boucle sur chaque case

                    if (i == 3) {
                        int dureeInt = (Integer) resultat.getObject(i);
                        int Minute_dureeS = dureeInt / 60;
                        int Seconde_dureeS = dureeInt % 60;
                        String dureeS = String.format("%02d:%02d", Minute_dureeS, Seconde_dureeS);
                        
                        row[i - 1] = dureeS;
                    } else {
                        row[i - 1] = resultat.getObject(i);
                    }
                    
                }
                ((DefaultTableModel) jTable1.getModel()).insertRow(resultat.getRow() - 1, row);
            }
            
            resultat.close();
            instruction.close();
            cnx.close();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
// TODO Auto-generated catch block 
            e1.printStackTrace();
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

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jTextFieldTitre = new javax.swing.JTextField();
        jTextFieldDuree = new javax.swing.JTextField();
        jTextFieldArtiste = new javax.swing.JTextField();
        jTextFieldAlbum = new javax.swing.JTextField();
        jButtonSupprimer = new javax.swing.JButton();
        jButtonModifier = new javax.swing.JButton();
        jButtonAjouter = new javax.swing.JButton();
        jLabelMessage = new javax.swing.JLabel();
        jButtonRecharger = new javax.swing.JButton();
        jComboBoxListGenre = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Importer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("chemin CSV : ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Titre", "Duree", "Artiste", "Album", "Genre"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Id");

        jLabel3.setText("Titre");

        jLabel4.setText("Duree");

        jLabel5.setText("Artiste");

        jLabel6.setText("Album");

        jLabel7.setText("Genre");

        jTextFieldId.setEditable(false);

        jTextFieldTitre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTitreActionPerformed(evt);
            }
        });

        jTextFieldDuree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDureeActionPerformed(evt);
            }
        });

        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerActionPerformed(evt);
            }
        });

        jButtonModifier.setText("Modifier");
        jButtonModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierActionPerformed(evt);
            }
        });

        jButtonAjouter.setText("Ajouter");
        jButtonAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterActionPerformed(evt);
            }
        });

        jLabelMessage.setText("Message");

        jButtonRecharger.setText("Recharger");
        jButtonRecharger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRechargerActionPerformed(evt);
            }
        });

        jComboBoxListGenre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "genre" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldId, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(jTextFieldTitre)
                                    .addComponent(jTextFieldDuree)
                                    .addComponent(jTextFieldAlbum)
                                    .addComponent(jTextFieldArtiste)
                                    .addComponent(jComboBoxListGenre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonSupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(jButtonModifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonAjouter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonRecharger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRecharger))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSupprimer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldDuree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModifier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldArtiste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAjouter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldAlbum))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMessage)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxListGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //affichage de l'arborescence sans verifier exist file ;)
        JFileChooser fileopen = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("c files", "c");
        fileopen.addChoosableFileFilter(filter);
        
        int ret = fileopen.showDialog(null, "Open file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            String csvFile = file.toString();
            // plus the rest of your code
            if (csvFile.equals(LastcsvFile)) {
                int answer = JOptionPane.showConfirmDialog(this, "Replace existing file?");
                if (answer == JOptionPane.OK_OPTION) {
                    jLabel1.setText(csvFile);
                    lireCSV(csvFile);
                }
                
            } else {
                LastcsvFile = csvFile;
                csvFile = file.toString();
                jLabel1.setText(csvFile);
                lireCSV(csvFile);
            }
            // do the rest of your code
        }
        //final String completeFileName = getResourcePath(file.toString());
        //String lastPath=getResourcePath(completeFileName);

    }
    
    public void lireCSV(String csvFile) {
        
        try {
            idtm = (DefaultTableModel) jTable1.getModel();
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                tabMusique = line.split(cvsSplitBy);
                /*----------------------------------------------------------*/

                /*----------------------------------------------------------*/
                //maListe.add(tabMusique);
                //implicitement ds le JTable
                idtm.addRow(tabMusique);
            }
            //System.out.println("model to Jtable Done");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccueilMusique.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AccueilMusique.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.rowAtPoint(evt.getPoint());
        //System.out.println(row);

        String id = jTable1.getValueAt(row, 0).toString();
        String titre = jTable1.getValueAt(row, 1).toString();
        String duree = jTable1.getValueAt(row, 2).toString();
        String artiste = jTable1.getValueAt(row, 3).toString();
        String album = jTable1.getValueAt(row, 4).toString();
        String genre = jTable1.getValueAt(row, 5).toString();
        
        jTextFieldId.setText(id);
        jTextFieldTitre.setText(titre);
        jTextFieldDuree.setText(duree);
        jTextFieldArtiste.setText(artiste);
        jTextFieldAlbum.setText(album);
        jComboBoxListGenre.setSelectedItem(genre);
        

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextFieldTitreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTitreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTitreActionPerformed

    private void jTextFieldDureeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDureeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDureeActionPerformed

    private void jButtonAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterActionPerformed
        int id_artiste = 2;
        int id_genre = 2;
        int id_album = 2;
// Connexion

        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            String lsDSN = "jdbc:mysql://localhost/musiques";
            Connection cn = DriverManager.getConnection(lsDSN, "root", "");
            
            cn.setAutoCommit(false);
            /*--------------------------------------------------------------*/
            PreparedStatement lpstArtiste = null;
            PreparedStatement lpstIdArtiste = null;
            PreparedStatement lpstIdArtiste2 = null;
            
            try {//je cherche l'id de lartiste soit disant il existe déja
                String lsSQLIdArtiste = "SELECT artiste.id_artiste FROM artiste WHERE artiste.prenom =?";
                lpstIdArtiste = cn.prepareStatement(lsSQLIdArtiste);
                String artiste = jTextFieldArtiste.getText().toString();
                lpstIdArtiste.setString(1, artiste);
                ResultSet resultat = lpstIdArtiste.executeQuery();
                if (resultat.next()) {
                    id_artiste = resultat.getInt(1);
                    System.out.println("oui il existe déja lartiste avec Id : " + id_artiste);
                } else {//donc il nexiste pas cet artiste deja, cad il est à ajouter
                    System.out.println("artiste à ajouter");
                    String lsSQL = "INSERT INTO artiste(prenom)VALUES (?)";
                    lpstArtiste = cn.prepareStatement(lsSQL);
                    String artisteNew = jTextFieldArtiste.getText().toString();
                    
                    lpstArtiste.setString(1, artisteNew);
                    lpstArtiste.executeUpdate();
                    //apres avoir lajouter je cherche son id
                    System.out.println("artiste déja ajouté , je te donne son id : " + artisteNew);
                    
                    String lsSQLIdartiste2 = "SELECT artiste.id_artiste FROM artiste WHERE artiste.prenom =?";
                    lpstIdArtiste2 = cn.prepareStatement(lsSQLIdartiste2);
                    String artiste2 = jTextFieldArtiste.getText().toString();
                    lpstIdArtiste2.setString(1, artiste2);
                    ResultSet resultat2 = lpstIdArtiste2.executeQuery();
                    if (resultat2.next()) {
                        id_artiste = resultat2.getInt(1);
                        System.out.println("voila son id : " + id_artiste);
                        
                    }
                }
                
            } finally {
                if (lpstArtiste != null) {
                    lpstArtiste.close();
                }
            }
            /*--------------------------------------------------------------*/
            //on ajoute pas les genres de musique
            /*--------------------------------------------------------------*/
            PreparedStatement lpstAlbum = null;
            PreparedStatement lpstIdGenre = null;
            System.out.println("on passe à genre...");
            try {
                String lsSQLIdGenre = "SELECT genre.id_genre FROM genre WHERE genre.titre =?";
                lpstIdGenre = cn.prepareStatement(lsSQLIdGenre);
                // String genre = jTextFieldGenre.getText().toString();
                String genre = jComboBoxListGenre.getSelectedItem().toString();
                lpstIdGenre.setString(1, genre);
                ResultSet resultat = lpstIdGenre.executeQuery();
                while (resultat.next()) {
                    id_genre = resultat.getInt(1);
                    System.out.println("voila le genre de ce piste : " + id_genre);
                }
                System.out.println("on passe à album...");
                
                String lsSQL = "INSERT INTO album(titre,id_artiste,id_genre)VALUES (?,?,?)";
                lpstAlbum = cn.prepareStatement(lsSQL);
                String album = jTextFieldAlbum.getText().toString();
                
                lpstAlbum.setString(1, album);
                lpstAlbum.setInt(2, id_artiste);
                lpstAlbum.setInt(3, id_genre);
                System.out.println("album :" + album + "id_artiste" + id_artiste + "id_genre" + id_genre);
                
                lpstAlbum.executeUpdate();
            } finally {
                if (lpstAlbum != null) {
                    lpstAlbum.close();
                }
            }
            /*--------------------------------------------------------------*/
            PreparedStatement lpst = null;
            PreparedStatement lsSQLIdAlbum = null;
            
            try {
                System.out.println("on passe à piste...");
                String lsAlbum = "SELECT album.id_album FROM album WHERE album.titre =?";
                lsSQLIdAlbum = cn.prepareStatement(lsAlbum);
                String album = jTextFieldAlbum.getText().toString();
                lsSQLIdAlbum.setString(1, album);
                ResultSet resultat = lsSQLIdAlbum.executeQuery();
                while (resultat.next()) {
                    id_album = resultat.getInt(1);
                    System.out.println("id_album : " + id_album);
                }
                
                String lsSQL = "INSERT INTO piste(titre,duree,id_album)VALUES (?,?,?)";
                lpst = cn.prepareStatement(lsSQL);
                String id = jTextFieldId.getText().toString();
                String titre = jTextFieldTitre.getText().toString();
                String dureeS = jTextFieldDuree.getText().toString();
                //conversion de la duree mm:ss en seconde ;)
                String dureeTab[] = dureeS.split(":");
                int minute = Integer.parseInt(dureeTab[0]) * 60;
                int seconde = Integer.parseInt(dureeTab[1]);
                int duree = minute + seconde;
                /*String id_albumS = jTextFieldAlbum.getText().toString();
                 int id_album = Integer.parseInt(id_albumS);
                 String id_genre = jTextFieldGenre.getText().toString();
                 int id_gen = Integer.parseInt(id_genre);*/
                
                lpst.setString(1, titre);
                lpst.setInt(2, duree);
                lpst.setInt(3, id_album);
                
                lpst.executeUpdate();
                System.out.println("titre : " + titre + "duree : " + duree + "id_album : " + id_album);
                
            } finally {
                if (lpst != null) {
                    lpst.close();
                }
            }
            
            cn.commit();
        } catch (Exception e) {
            //cn.rollback();
        } finally {
            // if (cn != null) {
            //cn.close();
            //}
        }
        

    }//GEN-LAST:event_jButtonAjouterActionPerformed

    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        try {
            // --- Connexion
            Class.forName("com.mysql.jdbc.Driver");
            String lsDSN = "jdbc:mysql://localhost/musiques";
            Connection cn = DriverManager.getConnection(lsDSN, "root", "");
            // --- Supprimer
            String lsSQL = "DELETE FROM piste WHERE piste.id_piste=?";
            
            PreparedStatement lpst = cn.prepareStatement(lsSQL);
            String pisteId = jTextFieldId.getText().toString();
            lpst.setString(1, pisteId);
            // --- Execution de la requete
            lpst.executeUpdate();
            lpst.close();
            cn.close();
            jLabelMessage.setText("track where ID is :" + pisteId + " is deleted successfully");
            // TODO add your handling code here:
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSupprimerActionPerformed

    private void jButtonRechargerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRechargerActionPerformed
        String pilote = "com.mysql.jdbc.Driver";
        try {
            Class.forName(pilote);
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/musiques", "root", "");
            java.sql.Statement instruction = cnx.createStatement();
            ResultSet resultat = instruction.executeQuery("SELECT piste.id_piste,piste.titre,piste.duree,artiste.prenom AS artiste,album.titre AS album,genre.titre AS genre FROM piste, album, genre, artiste WHERE piste.id_album=album.id_album AND album.id_genre=genre.id_genre AND album.id_artiste=artiste.id_artiste");

            //To remove previously added rows
            while (jTable1.getRowCount() > 0) {
                ((DefaultTableModel) jTable1.getModel()).removeRow(0);
            }
            int columns = resultat.getMetaData().getColumnCount();
            while (resultat.next()) {
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++) {
                    if (i == 3) {
                        int dureeInt = (Integer) resultat.getObject(i);
                        int Minute_dureeS = dureeInt / 60;
                        int Seconde_dureeS = dureeInt % 60;
                        String dureeS = String.format("%02d:%02d", Minute_dureeS, Seconde_dureeS);
                        
                        row[i - 1] = dureeS;
                    } else {
                        row[i - 1] = resultat.getObject(i);
                    }
                }
                ((DefaultTableModel) jTable1.getModel()).insertRow(resultat.getRow() - 1, row);
            }
            
            resultat.close();
            instruction.close();
            cnx.close();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
// TODO Auto-generated catch block 
            e1.printStackTrace();
        }
        

    }//GEN-LAST:event_jButtonRechargerActionPerformed

    private void jButtonModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierActionPerformed
        try {
            // --- Connexion
            Class.forName("com.mysql.jdbc.Driver");
            String lsDSN = "jdbc:mysql://localhost/musiques";
            Connection cn = DriverManager.getConnection(lsDSN, "root", "");
            //String lsSQL =" SELECT COUNT(idbureau) WHERE bureau.idarrondissement=arrondissements";
            String lsSQL = "UPDATE piste, artiste, album, genre SET piste.titre=?,"
                    + " piste.duree=?,"
                    + " artiste.prenom=?,"
                    + " album.titre=?,"
                    + " genre.titre=? "
                    + " WHERE piste.id_piste=? "
                    + " AND piste.id_album=album.id_album"
                    + " AND album.id_artiste=artiste.id_artiste"
                    + " AND album.id_genre=genre.id_genre";
            
            PreparedStatement lpst = cn.prepareStatement(lsSQL);
            String idS = jTextFieldId.getText().toString();
            int id = Integer.parseInt(idS);
            String titre = jTextFieldTitre.getText().toString();
            String dureeS = jTextFieldDuree.getText().toString();
            String album = jTextFieldAlbum.getText().toString();
            //String genre = jTextFieldGenre.getText().toString();
            String genre = jComboBoxListGenre.getSelectedItem().toString();
            String artiste = jTextFieldArtiste.getText().toString();
            
            lpst.setString(1, titre);
            //conversion de la duree mm:ss en seconde ;)
            String dureeTab[] = dureeS.split(":");
            int minute = Integer.parseInt(dureeTab[0]) * 60;
            int seconde = Integer.parseInt(dureeTab[1]);
            int duree = minute + seconde;
            
            lpst.setInt(2, duree);
            lpst.setString(3, artiste);
            lpst.setString(4, album);
            lpst.setString(5, genre);
            lpst.setInt(6, id);

            // --- Execution de la requete
            lpst.executeUpdate();
            jLabelMessage.setText("Updated successfully, ID :" + id);
            lpst.close();
            cn.close();

            // TODO add your handling code here:
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonModifierActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonRecharger;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JComboBox jComboBoxListGenre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldAlbum;
    private javax.swing.JTextField jTextFieldArtiste;
    private javax.swing.JTextField jTextFieldDuree;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldTitre;
    // End of variables declaration//GEN-END:variables
}
