
package proyecto_principal;

// The goat was here 

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Introduccion extends javax.swing.JFrame {

    public Introduccion() {
        initComponents();
        btn_sig_panel.setVisible(false);
        
        // Este cambio es para que se note en el git
        tf_numeroDeMaterias.getDocument().addDocumentListener( new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarInput();    
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarInput();    
            }
        
        
        });
        tf_calificacionDeseada.getDocument().addDocumentListener( new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarInput();    
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarInput();    
            }
        
        
        });
    }
    
    private void validarInput() {
        try{
            numero_de_materias = Integer.parseInt(tf_numeroDeMaterias.getText());
            calificacion_deseada = Integer.parseInt(tf_calificacionDeseada.getText());

            boolean numero_de_materias_es_correcta = 14 >= numero_de_materias && numero_de_materias >= 1;
            boolean calificacion_es_correcta = 100 >= calificacion_deseada && calificacion_deseada >= 60;
            boolean las_dos_entradas_son_validas = numero_de_materias_es_correcta && calificacion_es_correcta;
            
            btn_sig_panel.setVisible(las_dos_entradas_son_validas);
            
        }catch(NumberFormatException error){
            btn_sig_panel.setVisible(false);
            
        }
    }

    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel3 = new javax.swing.JPanel();
                label_titulo = new javax.swing.JLabel();
                btn_sig_panel = new javax.swing.JButton();
                panelTextfield = new javax.swing.JPanel();
                tf_numeroDeMaterias = new javax.swing.JTextField();
                tf_calificacionDeseada = new javax.swing.JTextField();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTextArea1 = new javax.swing.JTextArea();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setMaximumSize(new java.awt.Dimension(900, 700));
                setMinimumSize(new java.awt.Dimension(900, 700));
                setResizable(false);

                jPanel3.setBackground(Color.decode("#2C3333"));
                jPanel3.setForeground(new java.awt.Color(255, 255, 255));
                jPanel3.setMaximumSize(new java.awt.Dimension(900, 700));
                jPanel3.setPreferredSize(new java.awt.Dimension(900, 700));

                label_titulo.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
                label_titulo.setForeground(new java.awt.Color(255, 255, 255));
                label_titulo.setText("Calcula tu semestre!");

                btn_sig_panel.setBackground(Color.decode("#CBE4DE"));
                btn_sig_panel.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
                btn_sig_panel.setForeground(Color.decode("#2E4F4F"));
                btn_sig_panel.setText("Siguiente paso!");
                btn_sig_panel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_sig_panelActionPerformed(evt);
                        }
                });

                panelTextfield.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
                panelTextfield.setLayout(new javax.swing.BoxLayout(panelTextfield, javax.swing.BoxLayout.Y_AXIS));

                tf_numeroDeMaterias.setBackground(Color.decode("#2E4F4F"));
                tf_numeroDeMaterias.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
                tf_numeroDeMaterias.setForeground(new java.awt.Color(255, 255, 255));
                tf_numeroDeMaterias.setText("¿cuántas materias en total tienes en tu semestre?");
                tf_numeroDeMaterias.setBorder(null);
                tf_numeroDeMaterias.setOpaque(true);
                tf_numeroDeMaterias.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tf_numeroDeMateriasMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                tf_numeroDeMateriasMousePressed(evt);
                        }
                });
                tf_numeroDeMaterias.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                tf_numeroDeMateriasActionPerformed(evt);
                        }
                });
                panelTextfield.add(tf_numeroDeMaterias);

                tf_calificacionDeseada.setBackground(Color.decode("#2E4F4F"));
                tf_calificacionDeseada.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
                tf_calificacionDeseada.setForeground(new java.awt.Color(255, 255, 255));
                tf_calificacionDeseada.setText("¿Qué calificación quieres sacar en tu semestre?");
                tf_calificacionDeseada.setBorder(null);
                tf_calificacionDeseada.setOpaque(true);
                tf_calificacionDeseada.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tf_calificacionDeseadaMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                tf_calificacionDeseadaMousePressed(evt);
                        }
                });
                tf_calificacionDeseada.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                tf_calificacionDeseadaActionPerformed(evt);
                        }
                });
                panelTextfield.add(tf_calificacionDeseada);

                jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jScrollPane1.setForeground(new java.awt.Color(51, 51, 51));
                jScrollPane1.setOpaque(false);

                jTextArea1.setEditable(false);
                jTextArea1.setBackground(Color.decode("#2E4F4F"));
                jTextArea1.setColumns(20);
                jTextArea1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
                jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
                jTextArea1.setRows(5);
                jTextArea1.setText("Este programa calcula en base a tu numero de materias ingresadas y calificación deseada en tu semestre lo que deberias de \nsacar en cada materia.\n\nEl calculo es una aproximación.\n\n\t\t\t*El boton aparecerá si los datos son validos*\nDatos validos:\nnumero de materias -> 1 - 14\ncalificacion deseada -> 60 - 100");
                jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
                jTextArea1.setCaretColor(new java.awt.Color(0, 0, 0));
                jTextArea1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
                jTextArea1.setOpaque(false);
                jScrollPane1.setViewportView(jTextArea1);

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelTextfield, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(btn_sig_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(100, 100, 100))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(label_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(114, 114, 114)))
                                .addGap(237, 237, 237))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(label_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(panelTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(btn_sig_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(118, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void tf_calificacionDeseadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_calificacionDeseadaActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_tf_calificacionDeseadaActionPerformed

        private void tf_calificacionDeseadaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_calificacionDeseadaMousePressed
                // TODO add your handling code here:

                if(tf_calificacionDeseada.getText().equals("¿Qué calificación quieres sacar en tu semestre?")){
                        tf_calificacionDeseada.setText("");
                }
                tf_calificacionDeseada.setForeground(Color.white);

                if (tf_numeroDeMaterias.getText().equals("")){

                        tf_numeroDeMaterias.setForeground(Color.LIGHT_GRAY);
                        tf_numeroDeMaterias.setText("¿cuántas materias en total tienes en tu semestre?");

                }
        }//GEN-LAST:event_tf_calificacionDeseadaMousePressed

        private void tf_calificacionDeseadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_calificacionDeseadaMouseClicked
                // TODO add your handling code here:
        }//GEN-LAST:event_tf_calificacionDeseadaMouseClicked

        private void tf_numeroDeMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_numeroDeMateriasActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_tf_numeroDeMateriasActionPerformed

        private void tf_numeroDeMateriasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_numeroDeMateriasMousePressed
                if ( tf_numeroDeMaterias.getText().equals("¿cuántas materias en total tienes en tu semestre?")){
                        tf_numeroDeMaterias.setText("");
                }

                tf_numeroDeMaterias.setForeground(Color.white);

                if(tf_calificacionDeseada.getText().equals("")){

                        tf_calificacionDeseada.setForeground(Color.LIGHT_GRAY);
                        tf_calificacionDeseada.setText("¿Qué calificación quieres sacar en tu semestre?");
                }
        }//GEN-LAST:event_tf_numeroDeMateriasMousePressed

        private void tf_numeroDeMateriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_numeroDeMateriasMouseClicked
        }//GEN-LAST:event_tf_numeroDeMateriasMouseClicked

        private void btn_sig_panelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sig_panelActionPerformed
		boolean seSalio = false, materiaRepetida = false;
                int i = 0;
                materias = new Materia[numero_de_materias];

                while(i != numero_de_materias){

                        //nombreDeLasMaterias[i] = ;
                        materias[i] = new Materia(JOptionPane.showInputDialog("nombre de la materia "+ (i+1) + ": "));
			
			if(i > 0){
				String nombre = materias[i].getNombre();
				for (int j = 0; j < i; j++){
					if (nombre.equals(materias[j].getNombre())){
						materiaRepetida = true;	
						break;
					}
				}
			}
			if(materiaRepetida){
                                JOptionPane.showMessageDialog(this,"Ya existe esa materia, ingrese otra por favor");
				materiaRepetida = false;
				continue;
			}

                        if(materias[i].getNombre() == null){
				seSalio = true;
				break;
			}else if(materias[i].getNombre().trim().isBlank()){
                                JOptionPane.showMessageDialog(this,"El nombre de la materia "+(i+1)+" esta vacio, ingrese una entrada valida");
				continue;
			}
			i++;
                        
                }
		if (!seSalio){
			this.setVisible(false);

                	Evaluacion encuadre = new Evaluacion();
               		encuadre.setVisible(true);
                	encuadre.setLocationRelativeTo(null);
		}
        }//GEN-LAST:event_btn_sig_panelActionPerformed
    
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Introduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Introduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Introduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Introduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Introduccion proyecto = new Introduccion();
                proyecto.setVisible(true);
                proyecto.setLocationRelativeTo(null);
            }
        });
    }
    
    private static int numero_de_materias;
    private static float calificacion_deseada;
    public static Materia[] materias;

    public static int getNumero_de_materias() {
        return numero_de_materias;
    }

    public static float getCalificacion_deseada() {
        return calificacion_deseada;
    }
    
        // Variables declaration - do not modify//GEN-BEGIN:variables JAJAJA LOL
        private javax.swing.JButton btn_sig_panel;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextArea jTextArea1;
        private javax.swing.JLabel label_titulo;
        private javax.swing.JPanel panelTextfield;
        private javax.swing.JTextField tf_calificacionDeseada;
        private javax.swing.JTextField tf_numeroDeMaterias;
        // End of variables declaration//GEN-END:variables
}
