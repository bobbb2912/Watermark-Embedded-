package oop.wartermark;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.w3c.dom.css.RGBColor;

/**
 *
 * @author Hien PC
 */
public class MainForm extends javax.swing.JFrame {

    int width, height;
    int width_emb, height_emb;
    BufferedImage image_origin;
    BufferedImage image_embedded;
    BufferedImage redImg;
    BufferedImage blueImg;
    BufferedImage greenImg;

    public MainForm() {
        initComponents();
    }
    
    public BufferedImage RedImage(BufferedImage img) {

//        redImg = img;
        
        int w = redImg.getWidth();
        int h = redImg.getHeight();
        

        // convert to red image
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int p = redImg.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;

                // set new RGB keeping the r
                // value same as in original image
                // and setting g and b as 0.
                p = (a << 24) | (r << 16) | (0 << 8) | 0;

                redImg.setRGB(x, y, p);
                
            }
        }
        
        
        // write image
        try {
            File f = new File(
                    "D:\\Nam4\\Chuyen de\\DemoWatermark\\wartermark\\src\\main\\Image\\RedImage.png");
            ImageIO.write(redImg, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
        return redImg;
    }

    public BufferedImage GreenImage(BufferedImage img) {
//        greenImg = img;
        int w = greenImg.getWidth();
        int h = greenImg.getHeight();

        // convert to green image
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int p = greenImg.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int g = (p >> 8) & 0xff;

                // set new RGB
                // keeping the g value same as in original
                // image and setting r and b as 0.
                p = (a << 24) | (0 << 16) | (g << 8) | 0;

                greenImg.setRGB(x, y, p);
            }
        }
        // write image
        try {
            File f = new File(
                    "D:\\Nam4\\Chuyen de\\DemoWatermark\\wartermark\\src\\main\\Image\\GreenImage.png");
            ImageIO.write(greenImg, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
        return greenImg;
    }

    public BufferedImage BlueImage(BufferedImage img) {
        
//        blueImg = img;
        int w = blueImg.getWidth();
        int h = blueImg.getHeight();
      
        // convert to blue image
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int p = blueImg.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int b = p & 0xff;

                // set new RGB
                // keeping the b value same as in original
                // image and setting r and g as 0.
                p = (a << 24) | (0 << 16) | (0 << 8) | b;

                blueImg.setRGB(x, y, p);
            }
        }
        // write image
        try {
            File f = new File(
                    "D:\\Nam4\\Chuyen de\\DemoWatermark\\wartermark\\src\\main\\Image\\BlueImage.png");
            ImageIO.write(blueImg, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
        return blueImg;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOriginImg = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblOriginalImage = new javax.swing.JLabel();
        btnWatermarkImg = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblEmbImage = new javax.swing.JLabel();
        btnExtract = new javax.swing.JButton();
        btnEmb = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblAfterEmb = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblAfterExtract = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnOriginImg.setText("Chọn ảnh gốc");
        btnOriginImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOriginImgMouseClicked(evt);
            }
        });
        btnOriginImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOriginImgActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ảnh gốc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setName(""); // NOI18N

        lblOriginalImage.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOriginalImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOriginalImage, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnWatermarkImg.setText("Chọn ảnh nhúng");
        btnWatermarkImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnWatermarkImgMouseClicked(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ảnh nhúng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEmbImage, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEmbImage, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnExtract.setText("Trích xuất");

        btnEmb.setText("Nhúng ảnh");
        btnEmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmbActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ảnh sau khi nhúng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAfterEmb, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAfterEmb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ảnh trích xuất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblAfterExtract, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAfterExtract, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnOriginImg))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnExtract, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEmb, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btnWatermarkImg)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(btnEmb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btnExtract, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnWatermarkImg))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOriginImg)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOriginImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOriginImgMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOriginImgMouseClicked

    private void btnOriginImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOriginImgActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "gif");
        chooser.setFileFilter(imageFilter);
        chooser.setMultiSelectionEnabled(false); //chi cho phep chon 1 file

        int x = chooser.showDialog(this, "Chọn File");
        if (x == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(f);
                image_origin = ImageIO.read(f);
                redImg = ImageIO.read(f);
                greenImg = ImageIO.read(f);
                blueImg = ImageIO.read(f);
                
                width = image.getWidth();
                height = image.getHeight();

                System.out.println("Width_origin = " + width + " Height_origin = " + height);
                // chuyen hinh anh thanh mot mang cac byte;
//                byte[] byteImage = ImageHelper.toByteArray(image);

                try {
                    File file = new File(
                            "D:\\Nam4\\Chuyen de\\DemoWatermark\\wartermark\\src\\main\\Image\\Image_Origin.png");
                    ImageIO.write(image_origin, "png", file);
                } catch (IOException e) {
                    System.out.println(e);
                }
                // hien thi original image len form
                ImageIcon icon = new ImageIcon(f.getPath());
                Image img = ImageHelper.resize(icon.getImage(), 243, 243); // resize
                ImageIcon resizedIcon = new ImageIcon(img);
                lblOriginalImage.setIcon(resizedIcon);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("ulatr lai loi a-.-");
            }
        }
    }//GEN-LAST:event_btnOriginImgActionPerformed

    private void btnWatermarkImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnWatermarkImgMouseClicked
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "gif");
        chooser.setFileFilter(imageFilter);
        chooser.setMultiSelectionEnabled(false); //chi cho phep chon 1 file

        int x = chooser.showDialog(this, "Chọn File");
        if (x == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(f);
                image_embedded = ImageIO.read(f);
                width_emb = image.getWidth();
                height_emb = image.getHeight();

                System.out.println("Width_embedded = " + width_emb + " Height_embedded = " + height_emb);
                // chuyen hinh anh thanh mot mang cac byte;
//                byte[] byteImage = ImageHelper.toByteArray(image);


                try {
                    File file = new File(
                            "D:\\Nam4\\Chuyen de\\DemoWatermark\\wartermark\\src\\main\\Image\\Image_embedded.png");
                    ImageIO.write(image_embedded, "png", file);
                } catch (IOException e) {
                    System.out.println(e);
                }
                // hien thi original image len form
                ImageIcon icon = new ImageIcon(f.getPath());
                Image img = ImageHelper.resize(icon.getImage(), 243, 243); // resize
                ImageIcon resizedIcon = new ImageIcon(img);
                lblEmbImage.setIcon(resizedIcon);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("ulatr lai loi a-.-");
            }
        }
    }//GEN-LAST:event_btnWatermarkImgMouseClicked

//    static final int TYPE = BufferedImage.TYPE_INT_RGB;
//
//    public BufferedImage initial(BufferedImage img) {
//        BufferedImage dwtimage = null;
//        try {
//
////        BufferedImage buf=ImageIO.read(new File("lena.bmp"));
//            int w = img.getWidth();
//            int h = img.getHeight();
//            dwtimage = new BufferedImage(h, w, TYPE);
//            int[][] pixel = new int[h][w];
//            for (int x = 0; x < h; x++) {
//                for (int y = 0; y < w; y++) {
//                    pixel[x][y] = img.getRGB(x, y);
//
//                }
//            }
//            int[][] mat = new int[h][w];
//            int[][] mat2 = new int[h][w];
//
//            for (int a = 0; a < h; a++) {
//                for (int b = 0, c = 0; b < w; b += 2, c++) {
//                    mat[a][c] = (pixel[a][b] + pixel[a][b + 1]) / 2;
//                    mat[a][c + (w / 2)] = Math.abs(pixel[a][b] - pixel[a][b + 1]);
//                }
//            }
//            for (int p = 0; p < w; p++) {
//                for (int q = 0, r = 0; q < h; q += 2) {
//                    mat2[r][p] = (mat[q][p] + mat[q + 1][p]) / 2;
//                    mat2[r + (h / 2)][p] = Math.abs(mat[q][p] - mat[q + 1][p]);
//                }
//            }
//            for (int x = 0; x < h; x++) {
//                for (int y = 0; y < w; y++) {
//                    dwtimage.setRGB(x, y, mat2[x][y]);
//                }
//            }
//
////        String format="bmp";
////        ImageIO.write(dwtimage,format, new File("DWTIMAGE.bmp"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dwtimage;
//    }
//

     private static double[][] svd(double[][] imageMatrix, int rank) throws Exception {
        Jama.Matrix matrix = new Jama.Matrix(imageMatrix);

        int maxRank = matrix.rank();

        if (rank > maxRank) {
            rank = maxRank;
        }

        Jama.Matrix S = matrix.svd().getS();
        Jama.Matrix V = matrix.svd().getV();
        Jama.Matrix U = matrix.svd().getU();

//        S = new Jama.Matrix(Matrix.rankedMatrixS(new Matrix(S.getArray()), rank));
//        V = new Jama.Matrix(Matrix.rankedMatrixUV(new Matrix(V.getArray()), rank));
//        U = new Jama.Matrix(Matrix.rankedMatrixUV(new Matrix(U.getArray()), rank));

//        ImageIO.write(new Image().createImage(S.getArray()), "jpg", new File("src/images/S.jpg"));
//        ImageIO.write(new Image().createImage(V.getArray()), "jpg", new File("src/images/V.jpg"));
//        ImageIO.write(new Image().createImage(U.getArray()), "jpg", new File("src/images/U.jpg"));
//         try {
//            File f = new File(
//                    "D:\\Nam4\\Chuyen de\\DemoWatermark\\wartermark\\src\\main\\Image\\Image_QR.png");
//            ImageIO.write(S.getArray(), "png", f);
//        } catch (IOException e) {
//            System.out.println(e);
//        }

        Matrix composed = new Matrix(U.times(S).times(V.transpose()).getArray());
        return composed.getArray();
    }
    private void btnEmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmbActionPerformed
        // get color chanels
        redImg = RedImage(image_origin);
        greenImg = GreenImage(image_origin);
        blueImg = BlueImage(image_origin);
//        ImageIcon icon = new ImageIcon(image_origin);
//        Image img = ImageHelper.resize(icon.getImage(), 243, 243); // resize
//        ImageIcon resizedIcon = new ImageIcon(img);
//        lblAfterEmb.setIcon(resizedIcon);

///////////// DWT /////////////
        BufferedImage image_dwt = null;
        int w = image_origin.getWidth();
        int h = image_origin.getHeight();
        int[][] pixel = new int[h][w];
        image_dwt = new BufferedImage(h, w, BufferedImage.TYPE_INT_RGB);

////                        System.out.println(image_origin.getRGB(511, 511));

     
//            for (int x = 0; x < h; x++) {
//                for (int y = 0; y < w; y++) {
//                    pixel[x][y] = image_origin.getRGB(x, y);
//
//                }
//            }
        int k = 0;
        for (int i = 0; i < h; i += 2) {
            
            for (int j = 0; j < w; j += 2) {
                // LL band
//                System.out.println("i="+i+", j="+j+", k="+k);
                pixel[k][j / 2] = (image_origin.getRGB(i, j) + image_origin.getRGB(i, j + 1) + image_origin.getRGB(i + 1, j) + image_origin.getRGB(i + 1, j + 1)) / 4;
//                System.out.println( image_origin.getRGB(i, j));
                // LH band
                pixel[h / 2 + k][j / 2] = (image_origin.getRGB(i, j) - image_origin.getRGB(i, j + 1) + image_origin.getRGB(i + 1, j) - image_origin.getRGB(i + 1, j + 1)) / 4;

                // HL band
                
                pixel[k][w / 2 + j / 2] = (image_origin.getRGB(i, j) + image_origin.getRGB(i, j + 1) - image_origin.getRGB(i + 1, j) - image_origin.getRGB(i + 1, j + 1)) / 4;

                // HH band
                pixel[h / 2 + k][w / 2 + j / 2] = (image_origin.getRGB(i, j) - image_origin.getRGB(i, j + 1) - image_origin.getRGB(i + 1, j) + image_origin.getRGB(i + 1, j + 1)) / 4;

            }
            k++;
        }
        System.out.println("DONE IMAGE DWT...");


        // setRGB cho image_dwt
        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                image_dwt.setRGB(x, y, pixel[x][y]);
//                System.out.println( pixel[x][y]);
            }
        }

//        ImageIcon icon = new ImageIcon(image_dwt);
//        Image img = ImageHelper.resize(icon.getImage(), 243, 243); // resize
//        ImageIcon resizedIcon = new ImageIcon(img);
//        lblAfterEmb.setIcon(resizedIcon);
        // write image DWT
        try {
            File f = new File(
                    "D:\\Nam4\\Chuyen de\\DemoWatermark\\wartermark\\src\\main\\Image\\Image_DWT.png");
            ImageIO.write(image_dwt, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
        
        
/////////////////////QR  DECOMPOSITION///////////////
        // proj_u = project operation => hay con goi la toan tu chieu proj_u = u/||u||
        double[][] a, proj_u = null; // a: cot cua ma tran A (A=QR); <aj, ej>: la he so hoi quy => o day ej duoc luu trong ma tran Q
        double[] u = new double[w/2];
        double[][] Q, R;
        R = new double[w/2][h/2];
        a = new double[w/2][h/2];
        Q = new double[w/2][h/2];
        //// dat tat ca cac gia tri trong R = 0
        for(int i = 0; i < w/2; i++) {
            for(int j = 0; j < h/2; j++) {
                R[i][j] = 0;
            }
        }
        // dat lại cac gia tri trong vector u la 0
        for(int i = 0; i < w/2; i++) {
            u[i] = 0;
        }
        
        // mang 2 chieu a la mang pixel cua image_dwt tai LL band
        for(int i = 0; i < w/2; i++) {
            for(int j = 0; j < h/2; j++) {
                a[i][j] = image_dwt.getRGB(i, j);
            }
        }
        
        int col = 0;
        while (col < w / 2) {
            double u_len = 0;
            if (col == 0) {
                for (int i = 0; i < w / 2; i++) {
                    u[i] = a[col][i];
                    u_len+= Math.pow(u[i], 2);
                }
                u_len = Math.sqrt(u_len);

                for(int i = 0; i < w/2; i++) {
                    Q[col][i] = (1/u_len)*u[i];
                }
                R[0][0] = u_len;
            } else {
                //
                for(int i = 0; i < col; i++) {
                    for(int j = 0; j < w/2; j++) {
                        u[j] = a[col][j];
                        R[col][i] += a[col][j]*Q[i][j]; // thu duoc <a[k+1], e[k]> => dong thoi day cung la gia tri cua R
                    }
                }
                
//                double[][] mul_R_u = null;
                
                //// dat tat ca cac gia tri trong mul_R_u = 0
//                for (int i = 0; i < w / 2; i++) {
//                    for (int j = 0; j < h / 2; j++) {
//                        mul_R_u[i][j] = 0;
//                    }
//                }
                // <a[k+1], e[k]>*e[k] => hay: <R[k],e[k-1]>*e[k]
                for(int i = 0; i < col; i++) {
                    for(int j = 0; j < w/2; j++) {
                        u[j] -= R[col][i]*Q[i][j];
                    }
                }
                
                for (int i = 0; i < w / 2; i++) {
                    u[i] = a[col][i];
                    u_len+= Math.pow(u[i], 2);
                }
                u_len = Math.sqrt(u_len);

                for(int i = 0; i < w/2; i++) {
                    Q[col][i] = (1/u_len)*u[i];
                }
                R[col][col] = u_len;
                

            }
            col++;

        }
        
//        for(int i = 0; i < w/2; i++) {
//            for(int j = 0; j <h/2; j++) {
//                System.out.println("Q["+i+"]["+j+"] = "+Q[i][j]);
//                System.out.println("R["+i+"]["+j+"] = " + R[i][j]);
//            }
//        }
            
        System.out.println("DONE IMAGE QR...");
        // setRGB cho image_QR
        BufferedImage image_QR;
        image_QR = new BufferedImage(h/2, w/2, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < w/2; x++) {
            for (int y = 0; y < h/2; y++) {
                image_QR.setRGB(x, y, (int) R[x][y]);
//                System.out.println( pixel[x][y]);
            }
        }
        
//        ImageIcon icon = new ImageIcon(image_QR);
//        Image img = ImageHelper.resize(icon.getImage(), 243, 243); // resize
//        ImageIcon resizedIcon = new ImageIcon(img);
//        lblAfterEmb.setIcon(resizedIcon);

           // write image QR
        try {
            File f = new File(
                    "D:\\Nam4\\Chuyen de\\DemoWatermark\\wartermark\\src\\main\\Image\\Image_QR.png");
            ImageIO.write(image_QR, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
        
////////////////////////SVD image origin///////////////////////
        
        double[][] svd = null;
//        Matrix R1 = new Matrix(R);
////        SingularValueDecomposition svd = new SingularValueDecomposition(R1);
//
//        Matrix U = svd.getU();
//        Matrix S = svd.getS();
//        Matrix V = svd.getV();
//        
//        
//        double[][] U1 = U.getArray();
//        double[][] S1 = S.getArray();
//        double[][] V1 = V.getArray();
//        
        
//        for (int x = 0; x < w/2; x++) {
//            for (int y = 0; y < h/2; y++) {
//                System.out.println(U1[x][y]);
//                System.out.println(S1[x][y]);
//                System.out.println(V1[x][y]);
//
////                System.out.println( pixel[x][y]);
//            }
//        }

        try {
            //////////////
            svd = svd(R, 1);
              
        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        // setRGB cho image_SVD
        BufferedImage image_SVD;
        image_SVD = new BufferedImage(h/2, w/2, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < w/2; x++) {
            for (int y = 0; y < h/2; y++) {
                image_SVD.setRGB(x, y, (int) svd[x][y]);
//                System.out.println( pixel[x][y]);
            }
        }
//        ImageIcon icon = new ImageIcon(image_SVD);
//        Image img = ImageHelper.resize(icon.getImage(), 243, 243); // resize
//        ImageIcon resizedIcon = new ImageIcon(img);
//        lblAfterEmb.setIcon(resizedIcon);
////////////////////////SVD image embedded///////////////////////
        
//        
        double[][] matrix_emb = new double[height_emb][width_emb];
        for(int i = 0; i < height_emb; i++) {
            for(int j = 0; j < width_emb; j++) {
                matrix_emb[i][j] = image_embedded.getRGB(i, j);
            }
                
        }
//        Matrix R2 = new Matrix(matrix_emb);
//        SingularValueDecomposition svd2 = new SingularValueDecomposition(R2);
//
//        Matrix U2 = svd2.getU();
//        Matrix S2= svd2.getS();
//        Matrix V2 = svd.getV();
//        
//        double[][] U2_matrix = U2.getArray();
//        double[][] S2_matrix = S2.getArray();
//        double[][] V2_matrix = V2.getArray();
//        
//
//
//        double[][] S_new = new double[height_emb][width_emb];
//        
//        // setRGB cho image_SVD_emb
////        BufferedImage image_SVD_emb;
////        image_SVD_emb = new BufferedImage(height_emb, width_emb, BufferedImage.TYPE_INT_RGB);
////        
////        
//////////tinh toan S_new////////////
//        for (int x = 0; x < height_emb/2; x++) {
//            for (int y = 0; y < width_emb/2; y++) {
//                S_new[x][y] = S1[x][y] + (0.1*S2_matrix[x][y]);
//            }
//        }
//        
//        ////////
//        Matrixx S_matrix = new Matrixx(S_new);
//        Matrixx U_matrix = new Matrixx(U1);
//        Matrixx V_matrix = new Matrixx(V1);
//        
//        Matrixx D = new Matrixx(U1);
//        D.multiplyMatrix(S_matrix);
//        D.multiplyMatrix(V_matrix);
//        
////        double[][] D_matrix = D.transpose();
//        double[][] D_matrix = V2_matrix;
//
//        ////
//           // setRGB cho image_QR
//        BufferedImage image_SVD;
//        image_SVD = new BufferedImage(h/2, w/2, BufferedImage.TYPE_INT_RGB);
//        for (int x = 0; x < w/2; x++) {
//            for (int y = 0; y < h/2; y++) {
//                image_SVD.setRGB(x, y, (int) D_matrix[x][y]);
////                System.out.println( pixel[x][y]);
//            }
//        }
//        
//        
//        ImageIcon icon = new ImageIcon(image_SVD);
//        Image img = ImageHelper.resize(icon.getImage(), 243, 243); // resize
//        ImageIcon resizedIcon = new ImageIcon(img);
//        lblAfterEmb.setIcon(resizedIcon);
// 
//        
        //////////////
         
        double[][] svd_emb = null;

        try {
            //////////////
            svd_emb = svd(matrix_emb, 1);
              
        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        // setRGB cho image_SVD
        BufferedImage image_SVD_emb;
        image_SVD_emb = new BufferedImage(h/2, w/2, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < w/2; x++) {
            for (int y = 0; y < h/2; y++) {
                image_SVD_emb.setRGB(x, y, (int) svd_emb[x][y]);
//                System.out.println( pixel[x][y]);
            }
        }
        ImageIcon icon = new ImageIcon(image_SVD_emb);
        Image img = ImageHelper.resize(icon.getImage(), 243, 243); // resize
        ImageIcon resizedIcon = new ImageIcon(img);
        lblAfterEmb.setIcon(resizedIcon);
        
    }//GEN-LAST:event_btnEmbActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEmb;
    private javax.swing.JButton btnExtract;
    private javax.swing.JButton btnOriginImg;
    private javax.swing.JButton btnWatermarkImg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblAfterEmb;
    private javax.swing.JLabel lblAfterExtract;
    private javax.swing.JLabel lblEmbImage;
    private javax.swing.JLabel lblOriginalImage;
    // End of variables declaration//GEN-END:variables
}
