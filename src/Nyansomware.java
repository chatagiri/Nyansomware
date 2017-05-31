/*
    Version     : v0.1.0
    Nyansomware : javafxを用いてオーバレイ+フレームレス型のブラウザからYouTubeの猫プレイリストを再生します。
    Author      : chatagiriii @chatagiriii
    LastUpdate  : 2017/05/31
 */

import java.util.ArrayList;
import java.util.Collections;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.web.WebView;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.input.MouseEvent;

public class Nyansomware extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    private double xSize = 0;
    private double ySize = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        WebView webView = new WebView();
        Scene scene = new Scene(webView,320,240);

        //ウィンドウ内マウス降下時の処理
        webView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                // マウス降下時のx,y座標を取得
                xOffset = e.getSceneX();
                yOffset = e.getSceneY();
                // Sceneのx,y座標を取得する
                xSize = webView.getWidth();
                ySize = webView.getHeight();
            }
        });

        //ウィンドウ内マウスドラッグ時の処理
        webView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                // マウス降下座標がウィンドウ右下ならリサイズ
                if (xOffset > xSize * 0.9 && yOffset > ySize * 0.9) {
                    stage.setWidth(event.getX());
                    stage.setHeight(event.getY());
                } else if (xOffset < xSize * 0.1 && yOffset < ySize * 0.1) {
                    // マウス降下座標が左上ならウィンドウを移動
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            }
        });

        // 初期ページのURLを指定
        webView.getEngine().load(initUrl());

        // 常に前面に表示と表示処理
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    // URL周りの設定
    private String initUrl(){
        ArrayList<String> urlList = new ArrayList<String>();
        urlList.add("https://www.youtube.com/embed/-JR2lHJKRXA?&list=PL-eBqXlBVLey_9ZWEkIF78n_SsG_ukOoE&autoplay=1");
        urlList.add("https://www.youtube.com/embed/QFcHl11dX0M?&list=PLaDbCF2N-oO-1c6G6alo0F6dQMfNyyWG3&autoplay=1");
        Collections.shuffle(urlList);
        String url = urlList.get(0);
        return url;
    }
}

