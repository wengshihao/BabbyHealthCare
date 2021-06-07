package com.example.testapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


public class CircleFragment extends Fragment {
    RecyclerView circlerecyclerView;
    CircleAdapter circleAdapter;
    TextView circle_textview1;
    ImageButton circle_button_upload;
    CircleViewModel circleViewModel;

    public CircleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.circle_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        circleViewModel = ViewModelProviders.of(this).get(CircleViewModel.class);

        circle_button_upload = getView().findViewById(R.id.circle_button_upload);
//        circle_button_clear = getView().findViewById(R.id.circle_button_clear);
        circlerecyclerView = getView().findViewById(R.id.circle_recyclerview);
        circleAdapter = new CircleAdapter();
        circlerecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        circlerecyclerView.setAdapter(circleAdapter);



//        circlerecyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavController circleNavController = Navigation.findNavController(v);
//                circleNavController.navigate(R.id.action_circleFragment_to_circleFragment2);
//            }
//        });

        circleViewModel.  getAllCircleEntityLive().observe(getActivity(), new Observer<List<CircleEntity>>() {
            @Override
            public void onChanged(List<CircleEntity> circleEntities) {
                circleAdapter.setAllCircleEntity(circleEntities);
                circleAdapter.notifyDataSetChanged();
            }
        });


        circle_button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String[] text = {
//                        "目前研究认为遗尿症是一种复杂的疾病，与神经、泌尿、内分泌、心理、遗传、家庭生活培养方式不当等都有密切关系。多数孩子具有夜间尿液产生过多、夜间睡眠沉、叫醒困难的情况，有的孩子还存在功能性膀胱容量小，膀胱过度活动等。去氨加压素和遗尿报警器是目前多个国际儿童夜遗尿指南中的一线治疗方法。另外心理行为治疗、中医药治疗对部分患儿也有一定效果。无论采取哪种治疗方式，建立科学、合理的生活习惯，包括饮食、饮水、睡眠、排便习惯，都是最基础和最重要的治疗，部分遗尿症的孩子仅仅通过生活训练，尿床的症状就可能会有很大的改善。",
//                        "科学家探索婴儿的秘密世界，已有很长的历史。像达尔文就发表过一篇关于自己儿子的详细观察日记（\"他66天大的时候，我碰巧打了个喷嚏，他的反应很激烈\"）；达尔文的孩子们成了他建立进化论的助力。不过，或许因为婴孩不能表达自己的所想所感，科学家的探索史里也有不少莫名其妙的误解。在19和20世纪，很多科学家甚至觉得婴儿没有痛觉。",
//                        "婴儿是指 2 周岁以下没有自己座位的儿童。\n" +
//                                "2 至 11 周岁的所有乘客都被视为儿童。",
//                        "1月龄\n" +
//                                "1. 大动作\n" +
//                                "能部分控制头部，四肢可以弯曲。\n" +
//                                "2. 精细动作\n" +
//                                "动作无规律、不协调，小手经常握紧拳头。\n" +
//                                "3. 语言能力\n" +
//                                "能哭叫，可能会不自觉地笑，会与别人眼神交流，自己会发出细小的喉音，会倾听说话声。\n" +
//                                "4. 认知能力\n" +
//                                "对声音有反应，可能会向发出声音的地方转头，听见铃声时动作会放缓，被抱时会安静下来。\n" +
//                                "\n" +
//                                "2月龄\n" +
//                                "1. 大动作\n" +
//                                "四肢更加放松，平躺时可轻微抬头，俯卧时头可抬离床面。\n" +
//                                "2. 精细动作\n" +
//                                "小手部分打开，会抓大人的手指，眼睛会跟随大人移动。\n" +
//                                "3. 语言能力\n" +
//                                "能发出「咕咕」声，能与人保持眼神接触。\n" +
//                                "4. 认知能力\n" +
//                                "能微笑回应，也许还能大笑，表现出快乐、悲伤等情感，能立刻注意到大的玩具，喜欢触摸身边的物品。\n" +
//                                "\n" +
//                                "3月龄\n" +
//                                "1. 大动作\n" +
//                                "能从仰卧变成侧卧，俯卧时可抬头 45 °，竖抱时头比较稳当。\n" +
//                                "2. 精细动作\n" +
//                                "两手可握在一起，抓着东西会摇晃。\n" +
//                                "3. 语言能力\n" +
//                                "开始发出咿呀的声音，能笑出声音。\n" +
//                                "4. 认知能力\n" +
//                                "见人会笑，头可跟着看到的物品或听到的声音，转动 180°，开始注意到自己的手。",
//                        "1、刚出生的婴儿没有眼泪\n" +
//                                "2、刚出生的婴儿泪腺还没有开始发育，至少3星期以内都不会有眼泪。\n" +
//                                "3、刚出生的婴儿有300块骨头\n" +
//                                "4、正常成人共有206块骨头，婴儿发育过程中部分骨头会融合，最终变成206块。\n" +
//                                "5、刚出生的婴儿没有膝盖骨\n" +
//                                "6、刚出生的婴儿有类似膝盖的软骨组织，但是直到6个月，这些软骨才会发育。\n" +
//                                "7、5月出生的婴儿最胖\n" +
//                                "8、调查研究显示：5月出生的婴儿比其他月的婴儿都胖，大约胖200g。\n" +
//                                "9、婴儿大约有70种反射\n" +
//                                "10、婴儿的原始反射可以反映身体的健康状态，部分原始反射会逐步减弱或消失\n",
//                        "答案是300块！而成人后，骨头的数量将缩减成众所周知的206块。这是因为，婴儿身上有许多软骨，能保证婴儿的身体容易柔软地通过产道，也能带来惊人的灵活性。随着宝宝的成长和发育，一些软骨碎片也会融合在一起，慢慢地被坚硬的骨头所取代。\n"
//
//                };
//                for(int i=0; i<text.length;i++){
//                    circleViewModel.insertCircleEntity(new CircleEntity(text[i]));
//                }

                NavController circleNavController = Navigation.findNavController(v);
                circleNavController.navigate(R.id.action_circleFragment_to_circleFragment2);

            }
        });



    }
}