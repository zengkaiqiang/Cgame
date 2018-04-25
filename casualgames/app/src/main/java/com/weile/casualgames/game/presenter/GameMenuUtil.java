package com.weile.casualgames.game.presenter;

import com.weile.casualgames.R;
import com.weile.casualgames.chat.model.InteractiveMessage;
import com.weile.casualgames.chat.model.RecommendPlayer;
import com.weile.casualgames.game.model.GameMenu;
import com.weile.casualgames.game.model.Player;

import java.util.ArrayList;

/**
 * Created by zjj
 * 游戏菜单生成工具类
 */
public class GameMenuUtil {


    public static ArrayList<GameMenu> gameMenuList = new ArrayList<GameMenu>(){
        {
            add(new GameMenu(1, R.mipmap.ic_game_rank, R.string.game_menu_rank));
            add(new GameMenu(2, R.mipmap.ic_game_cook_house, R.string.game_menu_cook_house));
            add(new GameMenu(3, R.mipmap.ic_game_room, R.string.game_menu_room));
            add(new GameMenu(4, R.mipmap.ic_game_contact, R.string.game_menu_contact));
        }
    };


  /*  public static ArrayList<DoubleGame> doubleGameList = new ArrayList<DoubleGame>(){
        {
            add(new DoubleGame(1, R.mipmap.ic_game_start, 0, 0,0));
            add(new DoubleGame(2, R.mipmap.ic_game_double_a, R.string.game_double_a_title, 1246, R.drawable.shape_game_double_item_a));
            add(new DoubleGame(3, R.mipmap.ic_game_double_b, R.string.game_double_b_title, 12866, R.drawable.shape_game_double_item_b));
            add(new DoubleGame(4, R.mipmap.ic_game_double_c, R.string.game_double_c_title, 123546, R.drawable.shape_game_double_item_c));
            add(new DoubleGame(5, R.mipmap.ic_game_double_a, R.string.game_double_a_title, 1246, R.drawable.shape_game_double_item_d));
            add(new DoubleGame(6, R.mipmap.ic_game_double_b, R.string.game_double_b_title, 12866,R.drawable.shape_game_double_item_e));
            add(new DoubleGame(7, R.mipmap.ic_game_double_c, R.string.game_double_c_title, 123546, R.drawable.shape_game_double_item_e));
            add(new DoubleGame(8, R.mipmap.ic_game_double_a, R.string.game_double_a_title, 1246, R.drawable.shape_game_double_item_c));
            add(new DoubleGame(9, R.mipmap.ic_game_coming_soon, 0, 0, 0));
        }
    };*/


    public static ArrayList<RecommendPlayer> chatRecommendPlayerList = new ArrayList<RecommendPlayer>(){
        {
            add(new RecommendPlayer(1, R.mipmap.ic_default_avatar, "微微安"));
            add(new RecommendPlayer(2, R.mipmap.ic_default_avatar, "Mike"));
            add(new RecommendPlayer(3, R.mipmap.ic_default_avatar, "漫步于..."));
            add(new RecommendPlayer(4, R.mipmap.ic_default_avatar, "落叶归根"));
        }
    };


    public static ArrayList<InteractiveMessage> chatInteractiveMessageList = new ArrayList<InteractiveMessage>(){
        {
            add(new InteractiveMessage(1, R.mipmap.ic_chat_messgae, "互动消息", "付费199元共获得20000钻石", "下午2：55"));
            add(new InteractiveMessage(2, R.mipmap.ic_default_avatar, "Mark", "您已自动领取对方送的3朵花", "下午12：35"));
            add(new InteractiveMessage(3, R.mipmap.ic_default_avatar, "全村的希望", "Hi, 能加我好友嘛", "上午10：16"));
            add(new InteractiveMessage(4, R.mipmap.ic_default_avatar, "王子君","不服来战", "昨天 上午8：35"));
            add(new InteractiveMessage(4, R.mipmap.ic_default_avatar, "风中野百合", "一起来玩五子棋吧", "周一 下午10：16"));
        }
    };


    public static ArrayList<Player> gameRankToday = new ArrayList<Player>(){
        {
            add(new Player(4, "空心", R.mipmap.ic_default_avatar, 99865, 6));
            add(new Player(5, "糖宝VVW", R.mipmap.ic_default_avatar, 97865, 6));
            add(new Player(6, "空心", R.mipmap.ic_default_avatar, 97542, 6));
            add(new Player(7, "糖宝VVW", R.mipmap.ic_default_avatar, 89008, 6));
            add(new Player(8, "空心", R.mipmap.ic_default_avatar,88865, 6));
            add(new Player(9, "糖宝VVW", R.mipmap.ic_default_avatar,87865, 6));
            add(new Player(10, "空心", R.mipmap.ic_default_avatar,82542, 6));

        }
    };


    public static ArrayList<Player> gameRankYesterday = new ArrayList<Player>(){
        {
            add(new Player(4, "糖糖KILLY", R.mipmap.ic_rank_first_photo, 123256, 6));
            add(new Player(5, "仙女本仙", R.mipmap.ic_rank_first_photo, 123256, 6));
            add(new Player(6, "MIKE", R.mipmap.ic_rank_first_photo, 123256, 6));
            add(new Player(7, "糖糖KILLY", R.mipmap.ic_rank_first_photo, 123256, 6));
            add(new Player(8, "仙女本仙", R.mipmap.ic_rank_first_photo,123256, 6));
            add(new Player(9, "MIKE", R.mipmap.ic_rank_first_photo,123256, 6));


        }
    };

    public static ArrayList<Player> gameRankCelebrityList = new ArrayList<Player>(){
        {
            add(new Player("阿扎阿扎", R.mipmap.ic_rank_first_photo, "03/26"));
            add(new Player("糖糖KILLY", R.mipmap.ic_rank_first_photo, "03/25"));
            add(new Player("叶落知秋", R.mipmap.ic_rank_first_photo, "0324"));
            add(new Player("阿扎阿扎", R.mipmap.ic_rank_first_photo, "03/22"));
            add(new Player("叶落知秋", R.mipmap.ic_rank_first_photo,"03/21"));
            add(new Player("阿扎阿扎", R.mipmap.ic_rank_first_photo,"03/20"));
            add(new Player("糖糖KILLY", R.mipmap.ic_rank_first_photo,"03/19"));
            add(new Player("叶落知秋", R.mipmap.ic_rank_first_photo,"03/18"));
            add(new Player("阿扎阿扎", R.mipmap.ic_rank_first_photo,"03/17"));
            add(new Player("糖糖KILLY", R.mipmap.ic_rank_first_photo,"03/16"));
            add(new Player("叶落知秋", R.mipmap.ic_rank_first_photo,"03/15"));
            add(new Player("阿扎阿扎", R.mipmap.ic_rank_first_photo,"03/14"));
            add(new Player("糖糖KILLY", R.mipmap.ic_rank_first_photo,"03/13"));
            add(new Player("叶落知秋", R.mipmap.ic_rank_first_photo,"03/12"));


        }
    };


}
