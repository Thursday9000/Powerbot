public class GUI {

	@SuppressWarnings("serial")
	public class NewJFrame extends javax.swing.JFrame {

		public NewJFrame() {
			initComponents();
		}

		private void initComponents() {

			jButton1 = new javax.swing.JButton();
			jCheckBox1 = new javax.swing.JCheckBox();
			jLabel1 = new javax.swing.JLabel();

			setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

			jButton1.setText("Start");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					jButton1ActionPerformed(evt);
				}
			});

			jCheckBox1.setSelected(true);
			jCheckBox1.setText("Eat Food?");

			jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
			jLabel1.setText("Thurs's Goblin Killer");

			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
					getContentPane());
			getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout
					.createParallelGroup(
							javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(
							layout.createSequentialGroup()
									.addContainerGap()
									.addGroup(
											layout.createParallelGroup(
													javax.swing.GroupLayout.Alignment.LEADING)
													.addComponent(
															jButton1,
															javax.swing.GroupLayout.DEFAULT_SIZE,
															javax.swing.GroupLayout.DEFAULT_SIZE,
															Short.MAX_VALUE)
													.addGroup(
															javax.swing.GroupLayout.Alignment.TRAILING,
															layout.createSequentialGroup()
																	.addGap(0,
																			0,
																			Short.MAX_VALUE)
																	.addComponent(
																			jLabel1)))
									.addContainerGap())
					.addGroup(
							layout.createSequentialGroup()
									.addGap(28, 28, 28)
									.addComponent(jCheckBox1)
									.addContainerGap(
											javax.swing.GroupLayout.DEFAULT_SIZE,
											Short.MAX_VALUE)));
			layout.setVerticalGroup(layout
					.createParallelGroup(
							javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(
							javax.swing.GroupLayout.Alignment.TRAILING,
							layout.createSequentialGroup()
									.addContainerGap(
											javax.swing.GroupLayout.DEFAULT_SIZE,
											Short.MAX_VALUE)
									.addComponent(jLabel1)
									.addGap(18, 18, 18)
									.addComponent(jCheckBox1)
									.addPreferredGap(
											javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(jButton1).addContainerGap()));

			pack();
		}

		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
			if (jCheckBox1.isEnabled()) {
				Variables.eatMe = true;
			}

			setVisible(false);
		}

		private javax.swing.JButton jButton1;
		private javax.swing.JCheckBox jCheckBox1;
		private javax.swing.JLabel jLabel1;

	}
}