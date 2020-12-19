package com.sama.communicationclass.Data;


import com.sama.communicationclass.R;

import java.util.ArrayList;

public class NanuliCardDecks {


    private static NanuliCardDecks one;

    public static NanuliCardDecks getInstance() {
        if (one == null) {
            one = new NanuliCardDecks();
        }
        return one;
    }

    int index = -1;
    private ArrayList<NanuliCardDeck> decks = new ArrayList<>();

    NanuliCardDecks(){

        ArrayList<NanuliCard> fruit = new ArrayList<>();
        ArrayList<NanuliCard> feeling = new ArrayList<>();
        ArrayList<NanuliCard> person = new ArrayList<>();
        ArrayList<NanuliCard> thing = new ArrayList<>();
        ArrayList<NanuliCard> food = new ArrayList<>();
        ArrayList<NanuliCard> daily = new ArrayList<>();
        ArrayList<NanuliCard> place = new ArrayList<>();
        ArrayList<NanuliCard> hobby = new ArrayList<>();
        ArrayList<NanuliCard> number = new ArrayList<>();




        fruit.add(new NanuliCard(R.drawable.fruit_apple,R.raw.voice_fruit_apple,"사과"));
        fruit.add(new NanuliCard(R.drawable.fruit_grape,R.raw.voice_fruit_grape,"포도"));
        fruit.add(new NanuliCard(R.drawable.fruit_mango,R.raw.voice_fruit_mango,"망고"));
        fruit.add(new NanuliCard(R.drawable.fruit_strawberry,R.raw.voice_fruit_strawberry,"딸기"));
        fruit.add(new NanuliCard(R.drawable.fruit_watermelon,R.raw.voice_fruit_watermelon,"수박"));
        feeling.add(new NanuliCard(R.drawable.feeling_good,R.raw.voice_feeling_good,"좋아요"));
        feeling.add(new NanuliCard(R.drawable.feeling_happy,R.raw.voice_feeling_happy,"기뻐요"));
        feeling.add(new NanuliCard(R.drawable.feeling_love,R.raw.voice_feeling_love,"사랑해요"));
        feeling.add(new NanuliCard(R.drawable.feeling_funny,R.raw.voice_feeling_funny,"재미있어요"));
        feeling.add(new NanuliCard(R.drawable.feeling_shy,R.raw.voice_feeling_shy,"부끄러워요"));
        feeling.add(new NanuliCard(R.drawable.feeling_hate,R.raw.voice_feeling_hate,"싫어요"));
        feeling.add(new NanuliCard(R.drawable.feeling_angry,R.raw.voice_feeling_angry,"화나요"));
        feeling.add(new NanuliCard(R.drawable.feeling_sad,R.raw.voice_feeling_sad,"슬퍼요"));
        feeling.add(new NanuliCard(R.drawable.feeling_sick,R.raw.voice_feeling_sick,"아파요"));
        feeling.add(new NanuliCard(R.drawable.feeling_scary,R.raw.voice_feeling_scary,"무서워요"));
        person.add(new NanuliCard(R.drawable.person_i,R.raw.voice_person_i,"나"));
        person.add(new NanuliCard(R.drawable.person_you,R.raw.voice_person_you,"너"));
        person.add(new NanuliCard(R.drawable.person_we,R.raw.voice_person_we,"우리"));
        person.add(new NanuliCard(R.drawable.person_friend,R.raw.voice_person_friend,"친구"));
        person.add(new NanuliCard(R.drawable.person_family,R.raw.voice_person_family,"가족"));
        person.add(new NanuliCard(R.drawable.person_mother,R.raw.voice_person_mother,"엄마"));
        person.add(new NanuliCard(R.drawable.person_father,R.raw.voice_person_father,"아빠"));
        person.add(new NanuliCard(R.drawable.person_sister1,R.raw.voice_person_sister1,"언니"));
        person.add(new NanuliCard(R.drawable.person_sister2,R.raw.voice_person_sister2,"누나"));
        person.add(new NanuliCard(R.drawable.person_brother1,R.raw.voice_person_brother1,"오빠"));
        person.add(new NanuliCard(R.drawable.person_brother2,R.raw.voice_person_brother2,"형"));
        person.add(new NanuliCard(R.drawable.person_brother3,R.raw.voice_person_brother3,"남동생"));
        person.add(new NanuliCard(R.drawable.person_sister3,R.raw.voice_person_sister3,"여동생"));
        person.add(new NanuliCard(R.drawable.person_grandmother,R.raw.voice_person_grandmother,"할머니"));
        person.add(new NanuliCard(R.drawable.person_grandfather,R.raw.voice_person_grandfather,"할아버지"));
        thing.add(new NanuliCard(R.drawable.thing_bag,R.raw.voice_thing_bag,"가방"));
        thing.add(new NanuliCard(R.drawable.thing_clock,R.raw.voice_thing_clock,"시계"));
        thing.add(new NanuliCard(R.drawable.thing_colored_pencil,R.raw.voice_thing_colored_pencil,"색연필"));
        thing.add(new NanuliCard(R.drawable.thing_paints,R.raw.voice_thing_paints,"물감"));
        thing.add(new NanuliCard(R.drawable.thing_crayon,R.raw.voice_thing_crayon ,"크레파스"));
        thing.add(new NanuliCard(R.drawable.thing_glue,R.raw.voice_thing_glue,"풀"));
        thing.add(new NanuliCard(R.drawable.thing_mask,R.raw.voice_thing_mask,"마스크"));
        thing.add(new NanuliCard(R.drawable.thing_paper,R.raw.voice_thing_paper,"종이"));
        thing.add(new NanuliCard(R.drawable.thing_computer,R.raw.voice_thing_computer,"컴퓨터"));
        thing.add(new NanuliCard(R.drawable.thing_scissors,R.raw.voice_thing_scissors,"가위"));
        thing.add(new NanuliCard(R.drawable.thing_toy,R.raw.voice_thing_toy,"장난감"));
        thing.add(new NanuliCard(R.drawable.thing_umbrella,R.raw.voice_thing_umbrella,"우산"));
        food.add(new NanuliCard(R.drawable.food_bread,R.raw.voice_food_bread,"빵"));
        food.add(new NanuliCard(R.drawable.food_burger,R.raw.voice_food_burger,"햄버거"));
        food.add(new NanuliCard(R.drawable.food_cake,R.raw.voice_food_cake,"케이크"));
        food.add(new NanuliCard(R.drawable.food_fish_dish,R.raw.voice_food_fish_dish,"생선요리"));
        food.add(new NanuliCard(R.drawable.food_macaroon,R.raw.voice_food_macaroon,"마카롱"));
        food.add(new NanuliCard(R.drawable.food_samgyetang,R.raw.voice_food_samgyetang,"삼계탕"));
        food.add(new NanuliCard(R.drawable.food_skewer,R.raw.voice_food_skewer,"꼬치"));
        food.add(new NanuliCard(R.drawable.food_egg,R.raw.voice_food_egg,"계란"));
        food.add(new NanuliCard(R.drawable.food_juice,R.raw.voice_food_juice,"쥬스"));
        food.add(new NanuliCard(R.drawable.food_rice_roll,R.raw.voice_food_rice_roll ,"김밥"));
        food.add(new NanuliCard(R.drawable.food_meat,R.raw.voice_food_meat,"고기"));
        food.add(new NanuliCard(R.drawable.food_ramen,R.raw.voice_food_ramen,"라면"));
        food.add(new NanuliCard(R.drawable.food_rice,R.raw.voice_food_rice,"밥"));
        food.add(new NanuliCard(R.drawable.food_salad,R.raw.voice_food_salad,"샐러드"));
        food.add(new NanuliCard(R.drawable.food_sherbet,R.raw.voice_food_sherbet,"빙수"));


        daily.add(new NanuliCard(R.drawable.daily_buy,R.raw.voice_daily_buy,"사고싶어"));
        daily.add(new NanuliCard(R.drawable.daily_hello,R.raw.voice_daily_hello,"안녕"));
        daily.add(new NanuliCard(R.drawable.daily_bye,R.raw.voice_daily_bye,"잘가"));
        daily.add(new NanuliCard(R.drawable.daily_go,R.raw.voice_daily_go,"가고싶어"));
        daily.add(new NanuliCard(R.drawable.daily_welcome,R.raw.voice_daily_welcome ,"반가워"));
        daily.add(new NanuliCard(R.drawable.daily_love,R.raw.voice_daily_love,"사랑해"));
        daily.add(new NanuliCard(R.drawable.daily_secret,R.raw.voice_daily_secret,"비밀이야"));
        daily.add(new NanuliCard(R.drawable.daily_sick,R.raw.voice_daily_sick,"아파"));
        daily.add(new NanuliCard(R.drawable.daily_wash,R.raw.voice_daily_wash,"손씻었어"));
        daily.add(new NanuliCard(R.drawable.daily_with,R.raw.voice_daily_with,"함께하자"));
        daily.add(new NanuliCard(R.drawable.daily_cheer_up,R.raw.voice_daily_cheer_up,"힘내"));
        daily.add(new NanuliCard(R.drawable.daily_curious,R.raw.voice_daily_curious ,"궁금해"));
        daily.add(new NanuliCard(R.drawable.daily_eat,R.raw.voice_daily_eat,"먹고싶어"));
        daily.add(new NanuliCard(R.drawable.daily_do,R.raw.voice_daily_do,"하고싶어"));
        daily.add(new NanuliCard(R.drawable.daily_help,R.raw.voice_daily_help,"도와줘"));
        daily.add(new NanuliCard(R.drawable.daily_no,R.raw.voice_daily_no,"안돼"));
        daily.add(new NanuliCard(R.drawable.daily_miss_you,R.raw.voice_daily_miss_you,"보고싶어"));


        daily.add(new NanuliCard(R.drawable.daily_sorry,R.raw.voice_daily_sorry,"미안해"));




        place.add(new NanuliCard(R.drawable.place_academy,R.raw.voice_place_academy,"학원"));
        place.add(new NanuliCard(R.drawable.place_home,R.raw.voice_place_home,"집"));
        place.add(new NanuliCard(R.drawable.place_hospital,R.raw.voice_place_hospital,"병원"));
        place.add(new NanuliCard(R.drawable.place_kindergarten,R.raw.voice_place_kindergarten,"유치원"));
        place.add(new NanuliCard(R.drawable.place_park,R.raw.voice_place_park,"공원"));
        place.add(new NanuliCard(R.drawable.place_playground,R.raw.voice_place_playground,"놀이터"));
        place.add(new NanuliCard(R.drawable.place_store,R.raw.voice_place_store,"가게"));
        place.add(new NanuliCard(R.drawable.place_supermarket,R.raw.voice_place_supermarket,"마트"));
        place.add(new NanuliCard(R.drawable.place_rest_room,R.raw.voice_place_rest_room,"화장실"));
        hobby.add(new NanuliCard(R.drawable.hobby_book_reading,R.raw.voice_hobby_book_reading,"독서"));
        hobby.add(new NanuliCard(R.drawable.hobby_dance,R.raw.voice_hobby_dance,"율동"));
        hobby.add(new NanuliCard(R.drawable.hobby_play,R.raw.voice_hobby_play,"놀이"));
        hobby.add(new NanuliCard(R.drawable.hobby_song,R.raw.voice_hobby_song,"노래부르기"));
        hobby.add(new NanuliCard(R.drawable.hobby_swimming,R.raw.voice_hobby_swimming,"수영"));
        hobby.add(new NanuliCard(R.drawable.hobby_travel,R.raw.voice_hobby_travel,"여행"));
        hobby.add(new NanuliCard(R.drawable.hobby_youtube,R.raw.voice_hobby_youtube,"유튜브"));
        hobby.add(new NanuliCard(R.drawable.hobby_bike,R.raw.voice_hobby_bike,"자전거타기"));
        hobby.add(new NanuliCard(R.drawable.hobby_game,R.raw.voice_hobby_game,"게임"));
        hobby.add(new NanuliCard(R.drawable.hobby_run,R.raw.voice_hobby_run,"달리기"));
        hobby.add(new NanuliCard(R.drawable.hobby_soccer,R.raw.voice_hobby_soccer,"축구"));
        hobby.add(new NanuliCard(R.drawable.hobby_basketball,R.raw.voice_hobby_basketball,"농구"));
        hobby.add(new NanuliCard(R.drawable.hobby_baseball,R.raw.voice_hobby_baseball,"야구"));
        number.add(new NanuliCard(R.drawable.number_1,R.raw.voice_number_1,"1"));
        number.add(new NanuliCard(R.drawable.number_2,R.raw.voice_number_2,"2"));
        number.add(new NanuliCard(R.drawable.number_3,R.raw.voice_number_3,"3"));
        number.add(new NanuliCard(R.drawable.number_4,R.raw.voice_number_4,"4"));
        number.add(new NanuliCard(R.drawable.number_5,R.raw.voice_number_5,"5"));
        number.add(new NanuliCard(R.drawable.number_6,R.raw.voice_number_6,"6"));
        number.add(new NanuliCard(R.drawable.number_7,R.raw.voice_number_7,"7"));
        number.add(new NanuliCard(R.drawable.number_8,R.raw.voice_number_8,"8"));
        number.add(new NanuliCard(R.drawable.number_9,R.raw.voice_number_9,"9"));
        number.add(new NanuliCard(R.drawable.number_10,R.raw.voice_number_10,"10"));
        number.add(new NanuliCard(R.drawable.number_11,R.raw.voice_number_11,"11"));
        number.add(new NanuliCard(R.drawable.number_12,R.raw.voice_number_12,"12"));
        number.add(new NanuliCard(R.drawable.number_13,R.raw.voice_number_13,"13"));
        number.add(new NanuliCard(R.drawable.number_14,R.raw.voice_number_14,"14"));
        number.add(new NanuliCard(R.drawable.number_15,R.raw.voice_number_15,"15"));
        number.add(new NanuliCard(R.drawable.number_16,R.raw.voice_number_16,"16"));
        number.add(new NanuliCard(R.drawable.number_17,R.raw.voice_number_17,"17"));
        number.add(new NanuliCard(R.drawable.number_18,R.raw.voice_number_18,"18"));
        number.add(new NanuliCard(R.drawable.number_19,R.raw.voice_number_19,"19"));
        number.add(new NanuliCard(R.drawable.number_20,R.raw.voice_number_20,"20"));
        number.add(new NanuliCard(R.drawable.number_21,R.raw.voice_number_21,"21"));
        number.add(new NanuliCard(R.drawable.number_22,R.raw.voice_number_22,"22"));
        number.add(new NanuliCard(R.drawable.number_23,R.raw.voice_number_23,"23"));
        number.add(new NanuliCard(R.drawable.number_24,R.raw.voice_number_24,"24"));
        number.add(new NanuliCard(R.drawable.number_25,R.raw.voice_number_25,"25"));
        number.add(new NanuliCard(R.drawable.number_26,R.raw.voice_number_26,"26"));
        number.add(new NanuliCard(R.drawable.number_27,R.raw.voice_number_27,"27"));
        number.add(new NanuliCard(R.drawable.number_28,R.raw.voice_number_28,"28"));
        number.add(new NanuliCard(R.drawable.number_29,R.raw.voice_number_29,"29"));
        number.add(new NanuliCard(R.drawable.number_30,R.raw.voice_number_30,"30"));



        decks.add(new NanuliCardDeck("물건",thing));
        decks.add(new NanuliCardDeck("과일",fruit));
        decks.add(new NanuliCardDeck("기분",feeling));
        decks.add(new NanuliCardDeck("사람",person));
        decks.add(new NanuliCardDeck("음식",food));
        decks.add(new NanuliCardDeck("일상",daily));
        decks.add(new NanuliCardDeck("장소",place));
        decks.add(new NanuliCardDeck("취미",hobby));
        decks.add(new NanuliCardDeck("숫자",number));

    }

    public NanuliCardDeck getDeck(){
        return decks.get(index);
    }

    public NanuliCardDeck nextCatalog(){
        index++;
        if(index >= decks.size()){
            index = 0;
        }
         return decks.get(index);
    }


    public NanuliCardDeck previousCatalog(){
        index--;
        if(index <= -1){
            index = decks.size()-1;
        }
        return decks.get(index);
    }











}
