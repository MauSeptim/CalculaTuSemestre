
package proyecto_principal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Encuadre extends javax.swing.JPanel {
    
    DefaultTableModel model;
    
    public Encuadre(){
        
        this.model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
		    if (column == 1) {
			    return row != (getRowCount() - 1);
		    }
		    return false;
            }
        };
	
        
        initComponents();
        Evaluacion.btnOk.setVisible(false);
        btnEliminar.setVisible(false);
        observarCheckBoxes();
        
        model.addColumn("Criterios");
        model.addColumn("Ponderacion %");

        tableEvaluacionYponderacion.setModel(model);
        model.addTableModelListener(listener);
	tableEvaluacionYponderacion.getTableHeader().setDefaultRenderer(new CambiarEncabezadoDeLaTabla());


	tableEvaluacionYponderacion.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			if (row == table.getRowCount() - 1) {
				component.setBackground(Color.decode("#222831")); 
				component.setForeground(Color.decode("#00ADB5")); 
				component.setFont(new Font("Roboto", Font.BOLD, 18)); 
			} else {
				component.setBackground(Color.white);
				component.setForeground(Color.black);
				component.setFont(new Font("Roboto", Font.PLAIN, 18));
			}

			return component;
		}
	});


    }
    private class CambiarEncabezadoDeLaTabla extends DefaultTableCellRenderer	{
	public CambiarEncabezadoDeLaTabla(){
		setHorizontalAlignment(JLabel.CENTER);	
		setOpaque(true);
	}	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	    setBackground(Color.decode("#222831")); 
	    setForeground(Color.decode("#00ADB5")); 
	    setFont(new Font("Roboto", Font.BOLD, 16)); 

	    return this;
	}

    }
    
    private void observarCheckBoxes () {
        ManejarCheckBoxes manejar = new ManejarCheckBoxes();
        checkBoxProyectos.addActionListener(manejar);
        checkBoxPracticas.addActionListener(manejar);
        checkBoxTareasYAct.addActionListener(manejar);
        checkBoxActIntegradoras.addActionListener(manejar);
        checkBoxProyectoFinal.addActionListener(manejar);
        checkBoxExamen.addActionListener(manejar);
        checkBoxParticipacion.addActionListener(manejar);
        checkBoxAsistencia.addActionListener(manejar);
        checkBoxExpoSeminario.addActionListener(manejar);
    }
    private void desmarcarCheckBoxes (){
        checkBoxActIntegradoras.setSelected(false);
        checkBoxAsistencia.setSelected(false);
        checkBoxExamen.setSelected(false);
        checkBoxExpoSeminario.setSelected(false);
        checkBoxParticipacion.setSelected(false);
        checkBoxPracticas.setSelected(false);
        checkBoxProyectoFinal.setSelected(false);
        checkBoxProyectos.setSelected(false);
        checkBoxTareasYAct.setSelected(false);
    }
    private boolean esNumero(String cadena){
        for (char letra : cadena.toCharArray()){
            if (!Character.isDigit(letra)){
                return false;
            }    
        }
        return true;
    }
    private void actualizarCriterios(JCheckBox checkBox){
        cambioDeLaTablaPorCheckBox = true;
        boolean laTablaTieneNumeros = false;
        if (checkBox.isSelected()){
            
            for(int i = 0; i < model.getRowCount()-1; i++){
                laTablaTieneNumeros = esNumero(model.getValueAt(i, 1).toString());
                if(laTablaTieneNumeros) break;
                
            }
            for(int i = 0; i < model.getRowCount(); i++){
                if (model.getValueAt(i, 0).equals("TOTAL:")){
                    model.removeRow(i);
                   
                    break;
                }
            }
            Object[] filaNueva = {checkBox.getText(), "doble click para agregar tu porcentaje"};
            Object[] totalNuevo = {"TOTAL:", "sin valor"};
            
            if(ponderacionEsValida){
                ponderacionEsValida = false;
            }
            model.addRow(filaNueva);
            model.addRow(totalNuevo);
            
            if (laTablaTieneNumeros) actualizarCeldaTotal(sumaCeldaTotal+"");
            else actualizarCeldaTotal("0");
            
        }
        else{
            
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 0).equals(checkBox.getText())) {
                    boolean valorRemovidoTieneNumero = esNumero(model.getValueAt(i, 1).toString());
                    
                    if(valorRemovidoTieneNumero){
                        int numeroEliminado = Integer.parseInt(model.getValueAt(i, 1).toString());
                        sumaCeldaTotal -= numeroEliminado;
                        actualizarCeldaTotal(sumaCeldaTotal+"");
                    }
                    model.removeRow(i);
                    break;
                }
            }
            if(ponderacionEsValida){
                ponderacionEsValida = false;
            }
            if (model.getRowCount() == 1){
                model.removeRow(0);
            }
        }
        cambioDeLaTablaPorCheckBox = false;
    }
    
    private boolean noSeCumpleLaPonderacion (int ponderacionNoSumada, int numeroDeIteracion){
        int ultimaIteracion = model.getRowCount()-2;
        
        boolean noEsValido = (ponderacionNoSumada + sumaDePorcentajes != 100 && numeroDeIteracion == ultimaIteracion);
        sumaDePorcentajes += ponderacionNoSumada;
        
        return noEsValido;
    }
    
    private void actualizarCeldaTotal (String valorParaActualizar){
        
        model.setValueAt(valorParaActualizar, model.getRowCount()-1, 1);
    }
    
    private class ActualizarPorcentajesDeLaTabla implements TableModelListener{
        @Override
        public void tableChanged(TableModelEvent e) {
            
            if(model.getRowCount() != 0 && !cambioDeLaTablaPorCheckBox){
                
              sumaDePorcentajes = 0;
              porcentajesDePonderacion = new int[model.getRowCount()-1];
              criterios = new String[model.getRowCount()-1];
                
                for (int i = 0; i < model.getRowCount()-1; i++){
                    
                    String porcentajeString = model.getValueAt(i, 1).toString();

                    if (porcentajeString.trim().isBlank()) model.setValueAt("doble click para agregar tu porcentaje", i, 1);
                }

                for (int i = 0; i < model.getRowCount()-1; i++){
                    
                  String porcentaje = model.getValueAt(i, 1).toString();
                  
                  if (esNumero(porcentaje)){
                      int ponderacion = Integer.parseInt(porcentaje);

                      if(100 > ponderacion && ponderacion > 0){
                          if (noSeCumpleLaPonderacion (ponderacion, i)){
                              ponderacionEsValida = false;
                              break;
                          }
                          porcentajesDePonderacion[i] = ponderacion;
			  criterios[i] = model.getValueAt(i, 0).toString();
                      }
                      else{
                      ponderacionEsValida = false;
                      break;
                      }
                  }else{
                      ponderacionEsValida = false;
                      break;
                      
                  }
                  ponderacionEsValida = true;
                }
		if (ponderacionEsValida){
		Introduccion.materias[Evaluacion.getNumeroDeMateriaSeleccionada()].ponderaciones = porcentajesDePonderacion;
		Introduccion.materias[Evaluacion.getNumeroDeMateriaSeleccionada()].criterios = criterios;
		Introduccion.materias[Evaluacion.getNumeroDeMateriaSeleccionada()].sugestiones();
		}
		
                sumaCeldaTotal = 0;

                for (int i = 0; i < model.getRowCount()-1; i++){
                    
                    String porcentaje = model.getValueAt(i, 1).toString();

                    if(esNumero(porcentaje)){
                        sumaCeldaTotal += Integer.parseInt(porcentaje);
                        model.removeTableModelListener(this);
                        actualizarCeldaTotal(sumaCeldaTotal+"");
                        model.addTableModelListener(this);
                    }
                }
            }
        }   
    }
    
    private class ManejarCheckBoxes implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent evt) {
            boolean a = checkBoxProyectos.isSelected();
            boolean b = checkBoxPracticas.isSelected();
            boolean c = checkBoxTareasYAct.isSelected();
            boolean d = checkBoxActIntegradoras.isSelected();
            boolean e = checkBoxProyectoFinal.isSelected();
            boolean f = checkBoxExamen.isSelected();
            boolean g = checkBoxParticipacion.isSelected();
            boolean h = checkBoxAsistencia.isSelected();
            boolean i = checkBoxExpoSeminario.isSelected();
            
            cajaSeleccionada = (a||b||c||d||e||f||g||h||i);
            Evaluacion.btnOk.setVisible(cajaSeleccionada);
            btnEliminar.setVisible(cajaSeleccionada);
        }
        
    }
    
    
    
    
        /*boolean[] arreglo = {};*/
        
    

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jScrollPane1 = new javax.swing.JScrollPane();
                tableEvaluacionYponderacion = new javax.swing.JTable();
                panelCriterios = new javax.swing.JPanel();
                checkBoxExamen = new javax.swing.JCheckBox();
                checkBoxProyectoFinal = new javax.swing.JCheckBox();
                checkBoxActIntegradoras = new javax.swing.JCheckBox();
                checkBoxTareasYAct = new javax.swing.JCheckBox();
                checkBoxParticipacion = new javax.swing.JCheckBox();
                checkBoxAsistencia = new javax.swing.JCheckBox();
                checkBoxProyectos = new javax.swing.JCheckBox();
                checkBoxExpoSeminario = new javax.swing.JCheckBox();
                checkBoxPracticas = new javax.swing.JCheckBox();
                btnEliminar = new javax.swing.JButton();

                setBackground(new java.awt.Color(255, 255, 255));
                setMinimumSize(new java.awt.Dimension(953, 600));
                setPreferredSize(new java.awt.Dimension(953, 600));
                setVerifyInputWhenFocusTarget(false);
                setLayout(new java.awt.BorderLayout());

                jScrollPane1.setBorder(null);
                jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                tableEvaluacionYponderacion.setBackground(new java.awt.Color(255, 255, 255));
                tableEvaluacionYponderacion.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
                tableEvaluacionYponderacion.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Criterios de evaluación", "Ponderación %"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                tableEvaluacionYponderacion.setIntercellSpacing(new java.awt.Dimension(5, 1));
                tableEvaluacionYponderacion.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
                tableEvaluacionYponderacion.setRowHeight(29);
                tableEvaluacionYponderacion.setShowGrid(true);
                jScrollPane1.setViewportView(tableEvaluacionYponderacion);

                add(jScrollPane1, java.awt.BorderLayout.CENTER);

                panelCriterios.setBackground(Color.decode("#222831"));
                panelCriterios.setForeground(Color.decode("#EEEEEE"));
                panelCriterios.setMinimumSize(new java.awt.Dimension(100, 100));
                panelCriterios.setLayout(new java.awt.GridLayout(9, 1, 5, 5));

                checkBoxExamen.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
                checkBoxExamen.setForeground(Color.decode("#EEEEEE"));
                checkBoxExamen.setText("Examen");
                checkBoxExamen.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                checkBoxExamenActionPerformed(evt);
                        }
                });
                panelCriterios.add(checkBoxExamen);

                checkBoxProyectoFinal.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
                checkBoxProyectoFinal.setForeground(Color.decode("#EEEEEE"));
                checkBoxProyectoFinal.setText("Proyecto final");
                checkBoxProyectoFinal.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                checkBoxProyectoFinalActionPerformed(evt);
                        }
                });
                panelCriterios.add(checkBoxProyectoFinal);

                checkBoxActIntegradoras.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
                checkBoxActIntegradoras.setForeground(Color.decode("#EEEEEE"));
                checkBoxActIntegradoras.setText("Actividades integradoras");
                checkBoxActIntegradoras.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                checkBoxActIntegradorasActionPerformed(evt);
                        }
                });
                panelCriterios.add(checkBoxActIntegradoras);

                checkBoxTareasYAct.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
                checkBoxTareasYAct.setForeground(Color.decode("#EEEEEE"));
                checkBoxTareasYAct.setText("Tareas y actividades");
                checkBoxTareasYAct.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                checkBoxTareasYActActionPerformed(evt);
                        }
                });
                panelCriterios.add(checkBoxTareasYAct);

                checkBoxParticipacion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
                checkBoxParticipacion.setForeground(Color.decode("#EEEEEE"));
                checkBoxParticipacion.setText("Participación en clase");
                checkBoxParticipacion.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                checkBoxParticipacionActionPerformed(evt);
                        }
                });
                panelCriterios.add(checkBoxParticipacion);

                checkBoxAsistencia.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
                checkBoxAsistencia.setForeground(Color.decode("#EEEEEE"));
                checkBoxAsistencia.setText("Asistencia");
                checkBoxAsistencia.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                checkBoxAsistenciaActionPerformed(evt);
                        }
                });
                panelCriterios.add(checkBoxAsistencia);

                checkBoxProyectos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
                checkBoxProyectos.setForeground(Color.decode("#EEEEEE"));
                checkBoxProyectos.setText("Proyectos");
                checkBoxProyectos.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                checkBoxProyectosActionPerformed(evt);
                        }
                });
                panelCriterios.add(checkBoxProyectos);

                checkBoxExpoSeminario.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
                checkBoxExpoSeminario.setForeground(Color.decode("#EEEEEE"));
                checkBoxExpoSeminario.setText("Exposiciones / Seminarios");
                checkBoxExpoSeminario.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                checkBoxExpoSeminarioActionPerformed(evt);
                        }
                });
                panelCriterios.add(checkBoxExpoSeminario);

                checkBoxPracticas.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
                checkBoxPracticas.setForeground(Color.decode("#EEEEEE"));
                checkBoxPracticas.setText("Practicas");
                checkBoxPracticas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                checkBoxPracticasActionPerformed(evt);
                        }
                });
                panelCriterios.add(checkBoxPracticas);

                add(panelCriterios, java.awt.BorderLayout.WEST);

                btnEliminar.setBackground(Color.decode("#EEEEEE")
                );
                btnEliminar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
                btnEliminar.setForeground(Color.decode("#222831"));
                btnEliminar.setText("Eliminar todo");
                btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEliminarActionPerformed(evt);
                        }
                });
                add(btnEliminar, java.awt.BorderLayout.SOUTH);
        }// </editor-fold>//GEN-END:initComponents

    private void checkBoxProyectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxProyectosActionPerformed

        actualizarCriterios(checkBoxProyectos);
    }//GEN-LAST:event_checkBoxProyectosActionPerformed

    private void checkBoxPracticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxPracticasActionPerformed

        actualizarCriterios(checkBoxPracticas);
    }//GEN-LAST:event_checkBoxPracticasActionPerformed

    private void checkBoxTareasYActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxTareasYActActionPerformed

        actualizarCriterios(checkBoxTareasYAct);
    }//GEN-LAST:event_checkBoxTareasYActActionPerformed

    private void checkBoxActIntegradorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxActIntegradorasActionPerformed

        actualizarCriterios(checkBoxActIntegradoras);
    }//GEN-LAST:event_checkBoxActIntegradorasActionPerformed

    private void checkBoxProyectoFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxProyectoFinalActionPerformed

        actualizarCriterios(checkBoxProyectoFinal);
    }//GEN-LAST:event_checkBoxProyectoFinalActionPerformed

    private void checkBoxExamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxExamenActionPerformed

        actualizarCriterios(checkBoxExamen);
    }//GEN-LAST:event_checkBoxExamenActionPerformed

    private void checkBoxParticipacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxParticipacionActionPerformed

        actualizarCriterios(checkBoxParticipacion);
    }//GEN-LAST:event_checkBoxParticipacionActionPerformed

    private void checkBoxAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxAsistenciaActionPerformed

        actualizarCriterios(checkBoxAsistencia);
    }//GEN-LAST:event_checkBoxAsistenciaActionPerformed

    private void checkBoxExpoSeminarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxExpoSeminarioActionPerformed

        actualizarCriterios(checkBoxExpoSeminario);
    }//GEN-LAST:event_checkBoxExpoSeminarioActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        model.setRowCount(0);
        desmarcarCheckBoxes();
        btnEliminar.setVisible(false);
        Evaluacion.btnOk.setVisible(false);
        porcentajesDePonderacion = null;
    }//GEN-LAST:event_btnEliminarActionPerformed
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                JFrame ventana = new JFrame();
                ventana.add(new Encuadre());
                ventana.setSize(827, 555);
                ventana.setVisible(true);
                ventana.setLocationRelativeTo(null);
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        
        });
    }
    private int[] porcentajesDePonderacion = null;
    private ActualizarPorcentajesDeLaTabla listener = new ActualizarPorcentajesDeLaTabla(); 
    private int sumaDePorcentajes = 0;
    private int sumaCeldaTotal = 0;
    private boolean cajaSeleccionada = false;
    boolean ponderacionEsValida = false;

    private boolean cambioDeLaTablaPorCheckBox = false;
    private String[] criterios;
    
    
        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnEliminar;
        private javax.swing.JCheckBox checkBoxActIntegradoras;
        private javax.swing.JCheckBox checkBoxAsistencia;
        private javax.swing.JCheckBox checkBoxExamen;
        private javax.swing.JCheckBox checkBoxExpoSeminario;
        private javax.swing.JCheckBox checkBoxParticipacion;
        private javax.swing.JCheckBox checkBoxPracticas;
        private javax.swing.JCheckBox checkBoxProyectoFinal;
        private javax.swing.JCheckBox checkBoxProyectos;
        private javax.swing.JCheckBox checkBoxTareasYAct;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JPanel panelCriterios;
        private javax.swing.JTable tableEvaluacionYponderacion;
        // End of variables declaration//GEN-END:variables
}
