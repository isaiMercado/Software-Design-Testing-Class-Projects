package client.views.components;



import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.font.*;
import java.awt.event.*;

import javax.imageio.*;
import javax.swing.*;

import client.controllers.drawingComponent.DrawingListener;
import client.views.windows.ApplicationWindow;

import java.util.*;
import java.io.*;
import java.net.URL;


@SuppressWarnings("serial")
public class DrawingComponent extends JComponent {

	private static BufferedImage NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
	
	private ApplicationWindow appWindow;
	private int w_originX;
	private int w_originY;
	private double scale;
	
	private boolean dragging;
	private int w_dragStartX;
	private int w_dragStartY;
	private int w_dragStartOriginX;
	private int w_dragStartOriginY;
	private int w_dragEndX;
	private int w_dragEndY;

	private ArrayList<DrawingShape> shapes;
	private Font font;
	private BasicStroke stroke;
	
	private ArrayList<DrawingListener> listeners;
	private BufferedImage image;
	private boolean highLightsAllowed;
	private boolean imagenIsInverted;
	private DrawingRect currentRectangle;
	
	
	
	public DrawingComponent(ApplicationWindow window) {
		appWindow = window;
		w_originX = 0;
		w_originY = 0;
		scale = 1.0;
		highLightsAllowed = true;
		imagenIsInverted = false;
		initDrag();

		shapes = new ArrayList<DrawingShape>();
		font = new Font("SansSerif", Font.PLAIN, 72);
		stroke = new BasicStroke(5);
		listeners = new ArrayList<DrawingListener>();
		
		setBackground(UIManager.getColor("Button.darkShadow"));
		setPreferredSize(new Dimension(700, 700));
		setMinimumSize(new Dimension(100, 100));
		setMaximumSize(new Dimension(1000, 1000));
		
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		addComponentListener(componentAdapter);
		addMouseWheelListener(mouseAdapter);
	}
	
	
	
	private void initDrag() {
		dragging = false;
		w_dragStartX = 0;
		w_dragStartY = 0;
		w_dragStartOriginX = 0;
		w_dragStartOriginY = 0;
	}
	
	
	
	private void createTextShapes() {
		String text1 = "Width: " + this.getWidth();
		shapes.add(new DrawingText(text1, Color.BLACK, new Point2D.Float(200, 200)));
		
		String text2 = "Height: " + this.getHeight();
		shapes.add(new DrawingText(text2, Color.BLACK, new Point2D.Float(200, 400)));
	}
	
	
	
	private void updateTextShapes() {
		for (DrawingShape shape : shapes) {
			if (shape instanceof DrawingText) {
				DrawingText textShape = (DrawingText)shape;
				if (textShape.getText().startsWith("Width:")) {
					textShape.setText("Width: " + this.getWidth());
				}
				else if (textShape.getText().startsWith("Height:")) {
					textShape.setText("Height: " + this.getHeight());
				}
			}
		}
	}
	
	
	
	public void loadImage(String imageFile) {
		try {
			image = ImageIO.read(new URL(imageFile));
			shapes.add(new DrawingImage(image, new Rectangle2D.Double(0,0,image.getWidth(),image.getHeight())));
			addRectanglesToMainImage();
			appWindow.InitializingTableEntryPanel();
			appWindow.InitializinFormDataEntryPanel();
			appWindow.InitializingFieldHelpPanel();
			appWindow.getInfoManager().inizializeCellInformation();////initializong array of information
			this.repaint();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	 public void invertImage(String url) {
		 try {
			if (this.imagenIsInverted == false) this.imagenIsInverted = true;
			else this.imagenIsInverted = false;
			ToggleCurrentRectangle();
			
		        for (int x = 0; x < image.getWidth(); x++) {
		            for (int y = 0; y < image.getHeight(); y++) {
		                int rgba = image.getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(255 - col.getRed(),
		                                255 - col.getGreen(),
		                                255 - col.getBlue());
		                image.setRGB(x, y, col.getRGB());
		            }
		        }
		     shapes.set(0, new DrawingImage(image, new Rectangle2D.Double(0,0,image.getWidth(),image.getHeight())));
		     this.repaint();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 }
	
	 
	 
	public void addRectanglesToMainImage() {
		int rows = appWindow.getInfoManager().getImageInfo().getSucceed().getOutput().getNumRecords();
		int columns = appWindow.getInfoManager().getImageInfo().getSucceed().getOutput().getNumFields();
		
		appWindow.getInfoManager().setRows(rows);
		appWindow.getInfoManager().setColumns(columns);
		
		int oldFirstXCoord = appWindow.getInfoManager().getImageInfo().getSucceed().getOutput().getFields().get(0).getXcoord();
		int oldFirstYCoord = appWindow.getInfoManager().getImageInfo().getSucceed().getOutput().getFirstYcoord();
		
		int sumsOfWidthSoFar = 0; // sums of the different square's widths so far
		int sumsOfHeightSoFar = 0; // sum of the squares height;
		
		for(int a = 0 ; a < rows ; a++) {
			
			int oldHeight = appWindow.getInfoManager().getImageInfo().getSucceed().getOutput().getRecordHeight();
			
			for (int b = 0 ; b < columns ; b++) {
				
				int oldWidth = appWindow.getInfoManager().getImageInfo().getSucceed().getOutput().getFields().get(b).getPixelWidth();
				
				int oldLittleSquareXCoord = oldFirstXCoord + sumsOfWidthSoFar;
				int oldLittleSquareYCoord = oldFirstYCoord + sumsOfHeightSoFar;
				
				sumsOfWidthSoFar = sumsOfWidthSoFar + oldWidth;
			
				shapes.add(new DrawingRect(new Rectangle2D.Double(oldLittleSquareXCoord, oldLittleSquareYCoord, oldWidth, oldHeight), new Color(0, 0, 0, 0)));
				
				
				////System.out.println("row "+a+" col "+b+" width "+oldWidth+" height "+oldHeight+" xCoord "+oldLittleSquareXCoord+" yCoord "+oldLittleSquareYCoord);
				if (b == columns-1) 
					sumsOfWidthSoFar = 0;
			}
			sumsOfHeightSoFar = sumsOfHeightSoFar + oldHeight;
		}
	}
	
	
	
	public void setScale(double newScale) {
		scale = newScale;
		this.repaint();
	}
	
	
	
	public void addDoubleToOldScale(double addition) {
		scale = scale + addition;
		if (scale > 2) scale = 2;
		////System.out.println("zoomIn scale "+scale);
		this.repaint();
	}
	
	
	
	public void substractDoubleToOldScale(double substraction) {
		scale = scale - substraction;
		if (scale < .3) scale = .3;
		////System.out.println("zoomOut scale "+scale);
		this.repaint();
	}
	
	
	
	public void setOrigin(int w_newOriginX, int w_newOriginY) {
		w_originX = w_newOriginX;
		w_originY = w_newOriginY;
		this.repaint();
	}
	
	
	
	public void addDrawingListener(DrawingListener listener) {
		listeners.add(listener);
	}
	
	
	
	private void notifyOriginChanged(int w_newOriginX, int w_newOriginY) {
		for (DrawingListener listener : listeners) {
			listener.originChanged(w_newOriginX, w_newOriginY);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		drawBackground(g2);

		g2.translate(getWidth()/2, getHeight()/2);
		g2.scale(scale, scale);
		g2.translate(-w_originX, -w_originY);

		drawShapes(g2);
	}
	
	
	
	private void drawBackground(Graphics2D g2) {
		g2.setColor(getBackground());
		g2.fillRect(0,  0, getWidth(), getHeight());
	}

	
	
	private void drawShapes(Graphics2D g2) {
		for (DrawingShape shape : shapes) {
			shape.draw(g2);
		}
	}
	
	
	
	private void ToggleCurrentRectangle() {
		try {
			if (currentRectangle != null) {
				if (this.highLightsAllowed == true) {
					if (this.imagenIsInverted == false) {
						currentRectangle.setColor(new Color(0, 0, 255, 128));
					} else {
						currentRectangle.setColor(new Color(255, 255, 0, 128));
					}
					repaint();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean isHighLightsAllowed() {
		return highLightsAllowed;
	}



	public void setHighLightsAllowed(boolean highLightsAllowed) {
		this.highLightsAllowed = highLightsAllowed;
	}



	public boolean isImagenIsInverted() {
		return imagenIsInverted;
	}



	public void setImagenIsInverted(boolean imagenIsInverted) {
		this.imagenIsInverted = imagenIsInverted;
	}



	private void HighlightThisShape(DrawingShape selectedShape) {
		if (selectedShape != shapes.get(0)) {
		currentRectangle = (DrawingRect)selectedShape;
		
		for (DrawingShape shape : shapes) {
			if (shape != shapes.get(0))
			{
				((DrawingRect)shape).setColor(new Color(0,0,0,0));
			}
		}
		
		ToggleCurrentRectangle();
		}
	}
	
	
	
	private void FindColumnAndRowOfSelectedRectangle(DrawingShape selectedShape) {
		int columnIndex = 0;
		int rowIndex = 0;
		
		int columnCounter = 0;
		int rowCounter = 0;
		
		int imageNumOfColumns = appWindow.getInfoManager().getColumns();
		int imageNumOfRows = appWindow.getInfoManager().getRows();
		
		int listCounter = 0;
		
		boolean columnCounterAllowed = true;
		
		
		
		
		for (DrawingShape shape : shapes) {
			if(selectedShape == shape){
				int tempRow = (int)(listCounter / imageNumOfColumns);
				int tempCol = (listCounter % imageNumOfColumns);
				if (tempRow == 0)rowIndex = 0;     else rowIndex = tempRow;
				if (tempCol == 0) {columnIndex = imageNumOfColumns; rowIndex = rowIndex - 1;} else columnIndex = tempCol;
				break;
			}
			listCounter++;
			/*
			if (shape != shapes.get(0)) {
				if ((listCounter % imageNumOfColumns) == 0) {
					rowCounter++;
					columnCounter = 0;
					columnCounterAllowed = true;
				}
				if(selectedShape == shape){
					columnIndex = columnCounter;
					rowIndex = rowCounter;// - 1
					if (columnIndex < 0 ) columnIndex = 0;
					if (rowIndex < 0) rowIndex = 0;
					break;
				}
				if(columnCounterAllowed == true) {
					columnCounter++;
				}
			}
			listCounter++;
			if((listCounter % imageNumOfRows) == 0) {
				columnCounterAllowed = false;
			}
			*/
		}
		
		////System.out.println("drawing Component row" + rowIndex);
		//System.out.println("drawing Component col" + columnIndex);
		appWindow.getInfoManager().setCurrentSelectedRow(rowIndex);
		appWindow.getInfoManager().setCurrentSelectedCol(columnIndex);
	}
	
	
	
	public void setFocus(int row, int col) {
		int imageNumOfColumns = appWindow.getInfoManager().getColumns();
		int imageNumOfRows = appWindow.getInfoManager().getRows();
		
		int index = ((imageNumOfColumns * row) ) + col; // closer int index = ((imageNumOfColumns * row) + 1)
		
		//System.out.println("drawing index" + index);
		//System.out.println("drawing row" + row);
		//System.out.println("drawing col" + col+"\n\n\n");
		HighlightThisShape(shapes.get(index));
		
	}
	
	
	
	private MouseAdapter mouseAdapter = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {
			int d_X = e.getX();
			int d_Y = e.getY();
			
			AffineTransform transform = new AffineTransform();
			
			transform.translate(getWidth()/2, getHeight()/2);
			transform.scale(scale, scale);
			transform.translate(-w_originX, -w_originY);
			
			Point2D d_Pt = new Point2D.Double(d_X, d_Y);
			Point2D w_Pt = new Point2D.Double();
			try
			{
				transform.inverseTransform(d_Pt, w_Pt);
			}
			catch (NoninvertibleTransformException ex) {
				return;
			}
			w_dragEndX = (int)w_Pt.getX();
			w_dragEndY = (int)w_Pt.getY();
			
			dragging = true;		
			w_dragStartX = w_dragEndX;
			w_dragStartY = w_dragEndY;		
			w_dragStartOriginX = w_originX;
			w_dragStartOriginY = w_originY;
		}

		@Override
		public void mouseDragged(MouseEvent e) {		
			if (dragging) {
				int d_X = e.getX();
				int d_Y = e.getY();
				
				AffineTransform transform = new AffineTransform();
				
				transform.translate(getWidth()/2, getHeight()/2);
				transform.scale(scale, scale);
				transform.translate(-w_dragStartOriginX, -w_dragStartOriginY);
				
				Point2D d_Pt = new Point2D.Double(d_X, d_Y);
				Point2D w_Pt = new Point2D.Double();
				try
				{
					transform.inverseTransform(d_Pt, w_Pt);
				}
				catch (NoninvertibleTransformException ex) {
					return;
				}
				int w_X = (int)w_Pt.getX();
				int w_Y = (int)w_Pt.getY();
				
				int w_deltaX = w_X - w_dragStartX;
				int w_deltaY = w_Y - w_dragStartY;
				
				w_originX = w_dragStartOriginX - w_deltaX;
				w_originY = w_dragStartOriginY - w_deltaY;
				
				notifyOriginChanged(w_originX, w_originY);
				
				repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (highLightsAllowed == true) {
				if (w_dragEndX == w_dragStartX && w_dragEndY == w_dragStartY) {
					Graphics2D g2 = (Graphics2D)getGraphics();
					for (DrawingShape shape : shapes) {
						if (shape != shapes.get(0))
						{
							if (shape.contains(g2, w_dragEndX, w_dragEndY)) {
								HighlightThisShape(shape);
								FindColumnAndRowOfSelectedRectangle(shape);
								break;
							}
						}
					}
				}
			}
		}


		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.getWheelRotation() > 0) {
				scale = scale - .08;
			} else {
				scale = scale + .08;
			}
			
			if (scale > 2) scale = 2;
			if (scale < .3) scale = .3;
	        repaint();
			
	        /*
			//System.out.println("e.getScrollType() "+e.getScrollType()); 	
			//System.out.println("e.getWheelRotation() "+e.getWheelRotation()  );
			//System.out.println("e.getScrollAmount() "+e.getScrollAmount()); 	
			//System.out.println("e.getUnitsToScroll() "+e.getUnitsToScroll());
			*/
		}	
	};
	
	
	
	private ComponentAdapter componentAdapter = new ComponentAdapter() {

		@Override
		public void componentHidden(ComponentEvent e) {
			return;
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			return;
		}

		@Override
		public void componentResized(ComponentEvent e) {
			updateTextShapes();
		}

		@Override
		public void componentShown(ComponentEvent e) {
			return;
		}	
	};

	
	/////////////////
	// Drawing Shape
	/////////////////
	
	
	interface DrawingShape {
		boolean contains(Graphics2D g2, double x, double y);
		void draw(Graphics2D g2);
		Rectangle2D getBounds(Graphics2D g2);
	}


	class DrawingRect implements DrawingShape {

		private Rectangle2D rect;
		private Color color;
		
		public DrawingRect(Rectangle2D rect, Color color) {
			this.rect = rect;
			this.color = color;
		}

		@Override
		public boolean contains(Graphics2D g2, double x, double y) {
			return rect.contains(x, y);
		}

		@Override
		public void draw(Graphics2D g2) {
			g2.setColor(color);
			g2.fill(rect);
		}
		
		@Override
		public Rectangle2D getBounds(Graphics2D g2) {
			return rect.getBounds2D();
		}
		
		public Rectangle2D getRect() {
			return rect;
		}

		public void setRect(Rectangle2D rect) {
			this.rect = rect;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}
	}


	class DrawingLine implements DrawingShape {

		private Line2D line;
		private Color color;
		
		public DrawingLine(Line2D rect, Color color) {
			this.line = rect;
			this.color = color;
		}

		@Override
		public boolean contains(Graphics2D g2, double x, double y) {

			final double TOLERANCE = 5.0;
			
			Point2D p1 = line.getP1();
			Point2D p2 = line.getP2();
			Point2D p3 = new Point2D.Double(x, y);
			
			double numerator = (p3.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p3.getY() - p1.getY()) * (p2.getY() - p1.getY());
			double denominator =  p2.distance(p1) * p2.distance(p1);
			double u = numerator / denominator;
			
			if (u >= 0.0 && u <= 1.0) {
				Point2D pIntersection = new Point2D.Double(	p1.getX() + u * (p2.getX() - p1.getX()),
															p1.getY() + u * (p2.getY() - p1.getY()));
				
				double distance = pIntersection.distance(p3);
				
				return (distance <= TOLERANCE);
			}
			
			return false;
		}

		@Override
		public void draw(Graphics2D g2) {
			g2.setColor(color);
			g2.setStroke(stroke);
			g2.draw(line);
		}	
		
		@Override
		public Rectangle2D getBounds(Graphics2D g2) {
			return line.getBounds2D();
		}
	}


	class DrawingImage implements DrawingShape {

		private Image image;
		private Rectangle2D rect;
		
		public DrawingImage(Image image, Rectangle2D rect) {
			this.image = image;
			this.rect = rect;
		}

		@Override
		public boolean contains(Graphics2D g2, double x, double y) {
			return rect.contains(x, y);
		}

		@Override
		public void draw(Graphics2D g2) {
			g2.drawImage(image, (int)rect.getMinX(), (int)rect.getMinY(), (int)rect.getMaxX(), (int)rect.getMaxY(),
							0, 0, image.getWidth(null), image.getHeight(null), null);
		}	
		
		@Override
		public Rectangle2D getBounds(Graphics2D g2) {
			return rect.getBounds2D();
		}
	}


	class DrawingText implements DrawingShape {

		private String text;
		private Color color;
		private Point2D location;
		
		public DrawingText(String text, Color color, Point2D location) {
			this.text = text;
			this.color = color;
			this.location = location;
		}

		@Override
		public boolean contains(Graphics2D g2, double x, double y) {
			Rectangle2D bounds = getBounds(g2);
			return bounds.contains(x, y);
		}

		@Override
		public void draw(Graphics2D g2) {
			g2.setColor(color);
			g2.setFont(font);
			g2.drawString(text, (int)location.getX(), (int)location.getY());
		}
		
		@Override
		public Rectangle2D getBounds(Graphics2D g2) {
			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D bounds = font.getStringBounds(text, context);
			bounds.setRect(location.getX(), location.getY() + bounds.getY(), 
							bounds.getWidth(), bounds.getHeight());
			return bounds;
		}
		
		public String getText() {
			return text;
		}
		
		public void setText(String value) {
			text = value;
		}
	}


	public double getScale() {
		return scale;
	}



	public void ToggleHighLightsAllowed() {
		this.highLightsAllowed = !this.highLightsAllowed;
		for (DrawingShape shape : shapes) {
			if (shape != shapes.get(0))
			{
				((DrawingRect)shape).setColor(new Color(0,0,0,0));
			}
		}
		this.repaint();
	}



	
	
}





