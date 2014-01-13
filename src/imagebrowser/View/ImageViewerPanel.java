package imagebrowser.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class ImageViewerPanel extends ImageViewer {

    int x1;
    int x2;
    int x;
    private JPanel panel;
    private JPanel prevPanel = new JPanel();
    private JPanel nextPanel = new JPanel();

    @Override
    public void refresh() {
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graph) {
                graph.drawImage(getImage().getPrev().getImage(), -getImage().getPrev().getImage().getWidth() + x, 0, null);
                graph.drawImage(getImage().getImage(), x, 0, null);
                graph.drawImage(getImage().getNext().getImage(), getImage().getImage().getWidth() + x, 0, null);
                repaint();
            }
        };
        if (panel != null) {
            panel.add(imagePanel);
        }
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
        prevPanel.setBackground(Color.lightGray);
        nextPanel.setBackground(Color.lightGray);
        this.panel.add(prevPanel, BorderLayout.WEST);
        this.panel.add(nextPanel, BorderLayout.EAST);
        this.panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                setImage(getImage().getNext());
                getImage().setNext(getImage().getNext());
            }

            @Override
            public void mousePressed(MouseEvent me) {
                x1 = me.getX();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                x = 0;
                if (x1 - x2 > getImage().getImage().getWidth() / 2) {
                    setImage(getImage().getNext());
                    getImage().setNext(getImage().getNext());
                } else if (x2 - x1 > getImage().getImage().getWidth() / 2) {
                    setImage(getImage().getPrev());
                    getImage().setPrev(getImage().getPrev());
                }
                prevPanel.setBackground(Color.lightGray);
                nextPanel.setBackground(Color.lightGray);
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        this.panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
                x2 = me.getX();
                x = x2 - x1;
                if (x1 - x2 > getImage().getImage().getWidth() / 2) {
                    nextPanel.setBackground(Color.DARK_GRAY);
                } else {
                    nextPanel.setBackground(Color.lightGray);
                }

                if (x2 - x1 > getImage().getImage().getWidth() / 2) {
                    prevPanel.setBackground(Color.DARK_GRAY);
                } else {
                    prevPanel.setBackground(Color.lightGray);
                }
            }

            @Override
            public void mouseMoved(MouseEvent me) {
            }
        });
    }
}
