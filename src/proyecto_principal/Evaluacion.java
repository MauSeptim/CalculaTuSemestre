package proyecto_principal;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Evaluacion extends javax.swing.JFrame {

    public Evaluacion() {
        initComponents();
        
        
        cambioPorCodigo = true;
        
        if(numeroDeMaterias == 1){
            encuadre[0] = new Encuadre();
            comboBoxSeleccionarMateria.addItem(Introduccion.materias[0].getNombre());
            comboBoxSeleccionarMateria.setEnabled(false);
            
        }else{
            for (int i = 0; i < numeroDeMaterias; i++){
                encuadre[i] = new Encuadre();
                comboBoxSeleccionarMateria.addItem(Introduccion.materias[i].getNombre());
                
            } 
        }
        encuadre[0].setSize(953, 600);
        encuadre[0].setVisible(true);
        
        panelPadre.removeAll();
        panelPadre.add(encuadre[0], BorderLayout.CENTER);
        panelPadre.revalidate();
        panelPadre.repaint();
        
        
        cambioPorCodigo = false;
    }

    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel2 = new javax.swing.JPanel();
                panelPadre = new javax.swing.JPanel();
                panelComboYLabel = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                comboBoxSeleccionarMateria = new javax.swing.JComboBox<>();
                jLabel2 = new javax.swing.JLabel();
                btnOk = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setMinimumSize(new java.awt.Dimension(953, 700));

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setMinimumSize(new java.awt.Dimension(953, 700));
                jPanel2.setPreferredSize(new java.awt.Dimension(953, 700));
                jPanel2.setLayout(new java.awt.BorderLayout());

                panelPadre.setMinimumSize(new java.awt.Dimension(953, 600));
                panelPadre.setName(""); // NOI18N
                panelPadre.setPreferredSize(new java.awt.Dimension(953, 600));
                panelPadre.setLayout(new java.awt.BorderLayout());
                jPanel2.add(panelPadre, java.awt.BorderLayout.CENTER);

                panelComboYLabel.setBackground(Color.decode("#393E46"));
                panelComboYLabel.setMinimumSize(new java.awt.Dimension(953, 100));
                panelComboYLabel.setPreferredSize(new java.awt.Dimension(294, 100));
                panelComboYLabel.setLayout(new java.awt.GridLayout(2, 2, 3, 6));

                jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
                jLabel1.setForeground(Color.decode("#EEEEEE"));
                jLabel1.setText("Materia a elegir para personalizar su encuadre");
                panelComboYLabel.add(jLabel1);

                comboBoxSeleccionarMateria.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
                comboBoxSeleccionarMateria.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                comboBoxSeleccionarMateriaActionPerformed(evt);
                        }
                });
                panelComboYLabel.add(comboBoxSeleccionarMateria);

                jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
                jLabel2.setForeground(Color.decode("#EEEEEE"));
                jLabel2.setText("Escoja cuales criterios cumplen con el plan de evaluación de su materia");
                panelComboYLabel.add(jLabel2);

                btnOk.setText("Ok");
                btnOk.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnOkActionPerformed(evt);
                        }
                });
                panelComboYLabel.add(btnOk);

                jPanel2.add(panelComboYLabel, java.awt.BorderLayout.PAGE_START);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

    private void comboBoxSeleccionarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSeleccionarMateriaActionPerformed
        if(numeroDeMaterias > 1){
            numeroDeMateriaSeleccionada = comboBoxSeleccionarMateria.getSelectedIndex();
            if(!cambioPorCodigo){
                cambiarEncuadre(encuadre, numeroDeMateriaSeleccionada);
            }
        }
        
        
        
        
    }//GEN-LAST:event_comboBoxSeleccionarMateriaActionPerformed

        private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed

		boolean entradaValida = true;
		for (Encuadre encuadres : encuadre) {
			if (!encuadres.ponderacionEsValida) {
				entradaValida = false;
				break;
			}
		}
		if (entradaValida) {
			try {
				Document doc = new Document();
				PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Semestre.pdf"));
				doc.open();
					

				Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
				Font nombreMateriaFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, WebColors.getRGBColor("#FF0000"));


				Paragraph titulo = new Paragraph("Para conseguir "+(int)Introduccion.getCalificacion_deseada()+" en tu semestre necesitaras...", tituloFont);
				titulo.setAlignment(Element.ALIGN_CENTER);

				doc.add(titulo);
				doc.add(Chunk.NEWLINE);

				for (Materia materia : Introduccion.materias) {
					Paragraph nombreDeLaMateria = new Paragraph(materia.getNombre());
					nombreDeLaMateria.setFont(nombreMateriaFont);
					nombreDeLaMateria.setAlignment(Element.ALIGN_CENTER);
					doc.add(nombreDeLaMateria);
					doc.add(Chunk.NEWLINE);
					
					for (String sugestion: materia.sugestiones){
					Paragraph recomendaciones = new Paragraph(sugestion);
					doc.add(recomendaciones);
					}
					doc.add(Chunk.NEWLINE);
					

				}

				doc.close();
				writer.close();
				File jarFile = new File(Introduccion.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
				String jarPath = jarFile.getParentFile().getPath();

				File file = new File(jarPath + File.separator + "Semestre.pdf");
				
				Desktop.getDesktop().open(file);
				
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(rootPane, "Error: Archivo no encontrado");
			} catch (DocumentException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(rootPane, "Error al generar el PDF");
			} catch (IOException ex) {
				Logger.getLogger(Evaluacion.class.getName()).log(Level.SEVERE, null, ex);
			} catch (URISyntaxException ex) {
				Logger.getLogger(Evaluacion.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			JOptionPane.showMessageDialog(rootPane, "Hay encuadres que no suman 100");
		}
	
        }//GEN-LAST:event_btnOkActionPerformed
    private void cambiarEncuadre(Encuadre[] encuadre, int numeroDeMateriaSeleccionado){
        for (int i = 0; i < numeroDeMaterias; i++){
            if(encuadre[i].isVisible()){
                encuadre[i].setVisible(false);
                
                encuadre[numeroDeMateriaSeleccionado].setSize(827, 555);
                encuadre[numeroDeMateriaSeleccionado].setVisible(true);
                
                panelPadre.removeAll();
                panelPadre.add(encuadre[numeroDeMateriaSeleccionado], BorderLayout.CENTER);
                panelPadre.revalidate();
                panelPadre.repaint();
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(Evaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Evaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Evaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Evaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
           public void run() {
           Evaluacion encuadre = new Evaluacion();
           encuadre.setLocationRelativeTo(null);
           encuadre.setVisible(true);    
           }
        });
    }
    private final int numeroDeMaterias = Introduccion.getNumero_de_materias();
    private final Encuadre[] encuadre = new Encuadre[numeroDeMaterias];
    private boolean cambioPorCodigo = false;
    private static int numeroDeMateriaSeleccionada;

    public static int getNumeroDeMateriaSeleccionada() {
        return numeroDeMateriaSeleccionada;
    }
        // Variables declaration - do not modify//GEN-BEGIN:variables
        public static javax.swing.JButton btnOk;
        private javax.swing.JComboBox<String> comboBoxSeleccionarMateria;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel panelComboYLabel;
        private javax.swing.JPanel panelPadre;
        // End of variables declaration//GEN-END:variables
}
