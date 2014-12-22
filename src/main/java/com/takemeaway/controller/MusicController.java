package com.takemeaway.controller;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.takemeaway.model.Music;
import com.takemeaway.util.PrintTime;

import java.util.Date;

/**
 * Created by longjianlin on 14-7-25.
 * V 1.0
 * *********************************
 * Desc:
 * *********************************
 */
public class MusicController extends Controller {
    private static Logger logger = Logger.getLogger(MusicController.class);

    /**
     * 音乐首页
     */
    public void index() {
        try {
            long id = getParaToLong(0);
            Music music = Music.dao.getMusic(id);
            if (music != null) {
                music.set("create_at",
                        PrintTime.getNiceDate(music.getDate("create_at").toString()));
                setAttr("music", music);
                render("/music/music.html");
            } else {
                renderError(404);
            }
        } catch (Exception e) {
            logger.error("no music : " + e.getMessage());
            renderError(404);
        }
    }



    public void add() {
        render("/music/add.html");
    }

    /**
     * 保存音乐信息
     */
    public void save() {
        String title = getPara("title");
        String content = getPara("content");
        String url = getPara("url");
        String singer = getPara("singer");
        Music music = new Music();
        music.set("title", title);
        music.set("singer",singer);
        music.set("content", content);
        music.set("status", 1);
        music.set("url", url);
        music.set("create_at", new Date());

        boolean b = music.save();
        redirect("/");
    }

    /**
     * 编辑音乐信息
     */
    public void edit() {
        try {
            long id = getParaToLong(0);
            Music music = Music.dao.getMusic(id);
            setAttr("music", music);
            render("/music/edit.html");
        } catch (Exception e) {
            logger.error(e.getMessage());
            redirect("/music");
        }
    }

    /**
     * 更新音乐信息
     */
    public void update() {
        try {
            long id = getParaToLong("id");
            String title = getPara("title");
            String singer = getPara("singer");
            String content = getPara("content");
            String url = getPara("url");

            Music music = new Music();
            music.set("id", id);
            music.set("title", title);
            music.set("singer",singer);
            music.set("content", content);
            music.set("url", url);
            boolean b = music.update();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        redirect("/");
    }
}
