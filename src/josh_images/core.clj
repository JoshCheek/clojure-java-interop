(ns josh-images.core
  (:gen-class))

(import javax.imageio.ImageIO)
(import java.awt.image.BufferedImage)
(import java.io.File)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [w       500
        w-half  (/ w 2)
        h       500
        h-half  (/ h 2)
        iters   50000
        red     (int-array [255 0 0])
        white   (int-array [255 255 255])
        canvas  (BufferedImage. w h BufferedImage/TYPE_INT_RGB)
        pixels  (.getRaster canvas)
       ]
    (reduce (fn [_ i]
              (let [percent (/ i iters)
                    radians (* 2 Math/PI percent)
                    y (+ h-half (* (dec h-half) (Math/sin (* 13 radians))))
                    x (+ w-half (* (dec w-half) (Math/cos (* 17 radians))))
                   ]
                (.setPixel pixels (int x) (int y) white)
                )
              )
            nil
            (range iters))
    (ImageIO/write canvas , "gif" , (File. "lol.gif"))
  ))
