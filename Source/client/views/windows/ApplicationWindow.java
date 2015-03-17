package client.views.windows;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSplitPane;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.JToolBar;

import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import client.controllers.applicationWindow.DownloadBatchListener;
import client.controllers.applicationWindow.ExitButtonListener;
import client.controllers.applicationWindow.HighlightButtonListener;
import client.controllers.applicationWindow.InvertImageListener;
import client.controllers.applicationWindow.LogoutButtonListener;
import client.controllers.applicationWindow.SubmitButtonListener;
import client.controllers.applicationWindow.WindowClosingListener;
import client.controllers.applicationWindow.WindowResizeListener;
import client.controllers.applicationWindow.ZoomInListener;
import client.controllers.applicationWindow.ZoomOutListener;
import client.model.Manager;
import client.views.components.DrawingComponent;
import client.views.components.FieldHelpComponent;
import client.views.components.FormComponent;
import client.views.components.TableComponent;
import client.views.manager.InformationManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Color;

@SuppressWarnings("serial")
public class ApplicationWindow extends JFrame {
	
	
	
	private DownloadWindow downloadWindow;
	private InformationManager infoManager;
	
	private JPanel framePanel;
	private JSplitPane verticalSplitPane;
	private JSplitPane horizontalSplitPane;
	private JToolBar toolBar;
	private JPanel imagePanel;
	private JPanel southPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JTabbedPane leftTabbedPane;
	private JPanel leftPanelLeftTab;
	private JPanel leftPanelRightTab;
	private JTabbedPane rightTabbedPane;
	private JPanel rightPanelLeftTab;
	private JPanel rightPanelRightTab;
	private Image image;
	private JLabel labelWithImage;
	private DrawingComponent drawingComponent;
	private TableComponent table;
	private FormComponent form;
	private FieldHelpComponent helpComponent;
	private JButton btnSubmit;
	
	public ApplicationWindow(InformationManager manager) {
		setInfoManager(manager);
		addWindowListener( new WindowClosingListener());
		addComponentListener(new WindowResizeListener(this));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(new Dimension(800,600));
		setPreferredSize(new Dimension(800,600));
		setMinimumSize(new Dimension(300,300));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		framePanel = new JPanel();
		framePanel.setLayout(new GridLayout(1, 0, 0, 0));
		getContentPane().add(framePanel);
		
		
		verticalSplitPane = new JSplitPane();
		verticalSplitPane.setMinimumSize(new Dimension(200, 200));
		verticalSplitPane.setContinuousLayout(true);
		verticalSplitPane.setResizeWeight(0.5);
		verticalSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		framePanel.add(verticalSplitPane);
		
		imagePanel = new JPanel();
		imagePanel.setBackground(UIManager.getColor("Button.darkShadow"));
		imagePanel.setPreferredSize(new Dimension(verticalSplitPane.getLeftComponent().getWidth(),verticalSplitPane.getLeftComponent().getHeight()));//
		imagePanel.setMinimumSize(new Dimension(100,100));//
		imagePanel.setLayout(new BorderLayout(0, 0));
		verticalSplitPane.setLeftComponent(imagePanel);
		
		toolBar = new JToolBar();
		toolBar.setBackground(UIManager.getColor("Button.background"));
		toolBar.setFloatable(false);
		imagePanel.add(toolBar, BorderLayout.PAGE_START);
		
		JButton btnZoomIn = new JButton("Zoom In");
		btnZoomIn.addActionListener(new ZoomInListener(this));
		toolBar.add(btnZoomIn);
		
		JButton btnZoomOut = new JButton("Zoom Out");
		btnZoomOut.addActionListener(new ZoomOutListener(this));
		toolBar.add(btnZoomOut);
		
		JButton btnInvertImgae = new JButton("Invert Image");
		btnInvertImgae.addActionListener(new InvertImageListener());
		toolBar.add(btnInvertImgae);
		
		JButton btnNewButton = new JButton("Toogle Highlight");
		btnNewButton.addActionListener(new HighlightButtonListener());
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		toolBar.add(btnNewButton_1);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new SubmitButtonListener(this));
		toolBar.add(btnSubmit);
		
		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 0, 0, 0));
		southPanel.setPreferredSize(new Dimension(verticalSplitPane.getRightComponent().getWidth(),verticalSplitPane.getRightComponent().getHeight()));//
		southPanel.setMinimumSize(new Dimension(100,100));//
		verticalSplitPane.setRightComponent(southPanel);
		
		horizontalSplitPane = new JSplitPane();
		horizontalSplitPane.setDividerLocation(150);
		southPanel.add(horizontalSplitPane);
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(1, 0, 0, 0));
		leftPanel.setPreferredSize(new Dimension(horizontalSplitPane.getLeftComponent().getWidth(),horizontalSplitPane.getLeftComponent().getHeight()));
		leftPanel.setMinimumSize(new Dimension(100,100));
		horizontalSplitPane.setLeftComponent(leftPanel);
		
		
		leftTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		leftPanel.add(leftTabbedPane);
		
		leftPanelLeftTab = new JPanel();
		leftPanelLeftTab.setLayout(new BorderLayout());
		leftPanelLeftTab.setBackground(UIManager.getColor("Button.darkShadow"));
		leftTabbedPane.addTab("Table Entry", null, leftPanelLeftTab, null);
		
		leftPanelRightTab = new JPanel();
		leftPanelRightTab.setLayout(new BorderLayout());
		leftPanelRightTab.setBackground(UIManager.getColor("Button.darkShadow"));
		leftTabbedPane.addTab("Form Entry", null, leftPanelRightTab, null);
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(1, 0, 0, 0));
		rightPanel.setPreferredSize(new Dimension(horizontalSplitPane.getRightComponent().getWidth(),horizontalSplitPane.getRightComponent().getHeight()));
		rightPanel.setMinimumSize(new Dimension(100,100));
		horizontalSplitPane.setRightComponent(rightPanel);
		
		rightTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		rightTabbedPane.setToolTipText("");
		rightPanel.add(rightTabbedPane);
		
		rightPanelLeftTab = new JPanel();
		rightPanelLeftTab.setLayout(new BorderLayout());
		rightPanelLeftTab.setBackground(UIManager.getColor("Button.darkShadow"));
		rightTabbedPane.addTab("Field Help", null, rightPanelLeftTab, null);
		
		rightPanelRightTab = new JPanel();
		rightPanelRightTab.setBackground(UIManager.getColor("Button.darkShadow"));
		rightTabbedPane.addTab("Image Navigation", null, rightPanelRightTab, null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmDownloadBatch = new JMenuItem("Download Batch");
		mntmDownloadBatch.addActionListener(new DownloadBatchListener(this));
		mnFile.add(mntmDownloadBatch);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new LogoutButtonListener(this));
		mnFile.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ExitButtonListener());
		mnFile.add(mntmExit);
	
	}
	
	
	
	public void showDownloadBatchWindow() {
		downloadWindow = new DownloadWindow(this);
		downloadWindow.setVisible(true);
	}


	
	public JButton getBtnSubmit() {
		return btnSubmit;
	}
	
	

	public InformationManager getInfoManager() {
		return infoManager;
	}



	public void setInfoManager(InformationManager infoManager) {
		this.infoManager = infoManager;
	}
	
	
	public void OpenSampleWindow(String imageUrl) {
		downloadWindow.OpenSampleWindow(imageUrl);
	}



	public void CloseDownloadWindow() {
		downloadWindow.dispose();
	}



	public void LoadImage(String imageUrl) {
		try {
			String completeUrl = "http://"+infoManager.getHost()+":"+infoManager.getPort()+"/Records/"+imageUrl;
			drawingComponent = new DrawingComponent(this);
			drawingComponent.loadImage(completeUrl);
			
			imagePanel.removeAll();
			imagePanel.add(toolBar, BorderLayout.PAGE_START);
			imagePanel.add(drawingComponent);
			imagePanel.revalidate();
			imagePanel.repaint();
			pack();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}


	public DownloadWindow getDownloadWindow() {
		return downloadWindow;
	}



	public void setDownloadWindow(DownloadWindow downloadWindow) {
		this.downloadWindow = downloadWindow;
	}



	public JPanel getFramePanel() {
		return framePanel;
	}



	public void setFramePanel(JPanel framePanel) {
		this.framePanel = framePanel;
	}



	public JSplitPane getVerticalSplitPane() {
		return verticalSplitPane;
	}



	public void setVerticalSplitPane(JSplitPane verticalSplitPane) {
		this.verticalSplitPane = verticalSplitPane;
	}



	public JSplitPane getHorizontalSplitPane() {
		return horizontalSplitPane;
	}



	public void setHorizontalSplitPane(JSplitPane horizontalSplitPane) {
		this.horizontalSplitPane = horizontalSplitPane;
	}



	public JPanel getImagePanel() {
		return imagePanel;
	}



	public void setImagePanel(JPanel imagePanel) {
		this.imagePanel = imagePanel;
	}



	public JPanel getSouthPanel() {
		return southPanel;
	}



	public void setSouthPanel(JPanel southPanel) {
		this.southPanel = southPanel;
	}



	public JPanel getLeftPanel() {
		return leftPanel;
	}



	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}



	public JPanel getRightPanel() {
		return rightPanel;
	}



	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}



	public JTabbedPane getLeftTabbedPane() {
		return leftTabbedPane;
	}



	public void setLeftTabbedPane(JTabbedPane leftTabbedPane) {
		this.leftTabbedPane = leftTabbedPane;
	}



//	public JPanel getLeftPanelLeftTab() {
//		return leftPanelLeftTab;
//	}
//
//
//
//	public void setLeftPanelLeftTab(JPanel leftPanelLeftTab) {
//		this.leftPanelLeftTab = leftPanelLeftTab;
//	}



	public JPanel getLeftPanelRightTab() {
		return leftPanelRightTab;
	}



	public void setLeftPanelRightTab(JPanel leftPanelRightTab) {
		this.leftPanelRightTab = leftPanelRightTab;
	}



	public JTabbedPane getRightTabbedPane() {
		return rightTabbedPane;
	}



	public void setRightTabbedPane(JTabbedPane rightTabbedPane) {
		this.rightTabbedPane = rightTabbedPane;
	}



	public JPanel getRightPanelLeftTab() {
		return rightPanelLeftTab;
	}



	public void setRightPanelLeftTab(JPanel rightPanelLeftTab) {
		this.rightPanelLeftTab = rightPanelLeftTab;
	}



	public JPanel getRightPanelRightTab() {
		return rightPanelRightTab;
	}



	public void setRightPanelRightTab(JPanel rightPanelRightTab) {
		this.rightPanelRightTab = rightPanelRightTab;
	}



	public Image getImage() {
		return image;
	}



	public void setImage(Image image) {
		this.image = image;
	}



	public JLabel getLabelWithImage() {
		return labelWithImage;
	}



	public void setLabelWithImage(JLabel labelWithImage) {
		this.labelWithImage = labelWithImage;
	}



	public void ShowAssignedImageWindow() {
		JOptionPane.showMessageDialog(this, "You already have an Image assigned", "Error Message", JOptionPane.ERROR_MESSAGE, null);
	}



	public void ZoomIn(double scale) {
		drawingComponent.addDoubleToOldScale(scale);
	}
	
	
	public void ZoomOut(double scale) {
		drawingComponent.substractDoubleToOldScale(scale);
	}



	public void invertImage(String imageUrl) {
		String completeUrl = "http://"+infoManager.getHost()+":"+infoManager.getPort()+"/Records/"+imageUrl;
		drawingComponent.invertImage(completeUrl);
	}



	public void HighlightButtonActivation() {
		drawingComponent.ToggleHighLightsAllowed();
	}
	
	
	
	public void InitializingFieldHelpPanel() {
		rightPanelLeftTab.removeAll();
		helpComponent = new FieldHelpComponent(this);
		rightPanelLeftTab.add(helpComponent,BorderLayout.CENTER);
		
		rightPanelLeftTab.revalidate();
		rightPanelLeftTab.repaint();
	}
	
	
	
	public void InitializinFormDataEntryPanel() {
		leftPanelRightTab.removeAll();
		form = new FormComponent(this);
		leftPanelRightTab.add(form, BorderLayout.CENTER);
		
		leftPanelRightTab.revalidate();
		leftPanelRightTab.repaint();
	}
	
	
	
	public void InitializingTableEntryPanel() {
		table = new TableComponent(this);
		JScrollPane scrollPanel = new JScrollPane(table);
		leftPanelLeftTab.removeAll();
		leftPanelLeftTab.add(scrollPanel, BorderLayout.CENTER);
		
		leftPanelLeftTab.revalidate();
		leftPanelLeftTab.repaint();
	}
	
	
	
	public void UpdatingCoordinated_Table_Form_Image() {
		int currentSelectedRow = infoManager.getCurrentSelectedRow();
		int currentSelectedCol = infoManager.getCurrentSelectedCol();
		
		table.setFocus(currentSelectedRow, currentSelectedCol);
		form.setFocus(currentSelectedRow, currentSelectedCol);
		drawingComponent.setFocus(currentSelectedRow, currentSelectedCol);
	}
	
	
	public void ShowingFieldHelp(int column) {
		String url = infoManager.getImageInfo().getSucceed().getOutput().getFields().get(column - 1).getHelpUrl();
		//String helpHtmlFormat = Manager.getFieldHelps(url);
		
		System.out.println("fieldHelp "+ url);
		helpComponent.setText(url);
	}



	public void setValueInTable(int row, int col, String value) {
		this.table.setValueAt(value, row, col);
	}



	public void setValueInForm(int row, int col, String value) {
		this.form.refreshValues( row, col);
	}
	
	public TableComponent getTable() {
		return table;
	}



	public void setTable(TableComponent table) {
		this.table = table;
	}



	public FormComponent getForm() {
		return form;
	}



	public void setForm(FormComponent form) {
		this.form = form;
	}



	public FieldHelpComponent getHelpComponent() {
		return helpComponent;
	}



	public void setHelpComponent(FieldHelpComponent helpComponent) {
		this.helpComponent = helpComponent;
	}



	public void clean() {
		this.leftPanelLeftTab.removeAll();
		this.leftPanelRightTab.removeAll();
		this.rightPanelLeftTab.removeAll();
		this.rightPanelRightTab.removeAll();
		this.imagePanel.removeAll();
		
		imagePanel.add(toolBar, BorderLayout.PAGE_START);
		
		leftPanelLeftTab.repaint();
		leftPanelRightTab.repaint();
		rightPanelLeftTab.repaint();
		rightPanelRightTab.repaint();
		imagePanel.repaint();
	}


	






}
