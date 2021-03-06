package com.example.ks.moodle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ks.moodle.entity.Chapter;
import com.example.ks.moodle.entity.Lesson;
import com.example.ks.moodle.teacher_video.TeacherVideo;
import com.example.ks.moodle.teacher_video.TeacherVideo2;
import com.example.ks.moodle.teacher_video.TeacherVideo1_3;
import com.example.ks.moodle.teacher_video.TeacherVideo4;
import com.example.ks.moodle.util.TreeViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestExpendListView extends Activity {
    TreeViewAdapter adapter;
    ExpandableListView expandableListView;
    Button btnNormal;
    private TextView welcomeNameTv;
    private Button backTv;
    private List<String> groupList;
    private List<List<String>> childList;
    private List<Lesson>lessonList;
    private List<Chapter>chapterList;
/*
    public String[] groups={"第1章 软件工程学概述","第2章 可行性研究","第3章 需求分析","第4章 形式化说明技术",
            "第5章 总体设计","第6章 详细设计", "第7章 实现","第8章 维护","第9章 面向对象方法学引论","第10章 面向对象分析",
            "第11章 面向对象设计","第12章 面向对象实现"};
            */
    private String[] groups={"第1章 软件工程学概述","第2章 可行性研究","第3章 需求分析","第4章 形式化说明技术","第5章 总体设计",
        "第6章 详细设计", "第7章 实现","第8章 维护","第9章 面向对象方法学引论","第10章 面向对象分析",
        "第11章 面向对象设计","第12章 面向对象实现"};



    private String[][] child= {
            {"1.1 软件危机", "1.2 软件工程", "1.3 软件生命周期", "1.4 软件过程"},
            {"2.1 可行性研究的任务", "2.2 可行性研究过程", "2.3 系统流程图", "2.4 数据流图", "2.5 数据字典", "2.6 成本/效益分析"},
            {"3.1 需求分析的任务", "3.2 与用户沟通获取需求的方法", "3.3 分析建模与规格说明", "3.4 实体联系图", "3.5 数据规范化", "3.6 状态转换图", "3.7 其他图形工具", "3.8 验证软件需求"},
            {"4.1 概述", "4.2 有穷状态机", "4.3 Petri 网", "4.4 Z语言"},
            {"5.1 设计过程", "5.2 设计原理", "5.3 启发规则", "5.4 描绘软件结构的图形工具", "5.5 面向数据流的设计方法"},
            {"6.1 结构程序设计", "6.2 人机界面设计", "6.3 过程设计的工具", "6.4 面向数据结构的设计方法", "6.5 程序复杂程度的定量度量"},
            {"7.1 编码", "7.2 软件测试基础", "7.3 单元测试", "7.4 集成测试", "7.5 确认测试", "7.6 白盒测试技术", "7.7 黑盒测试技术", "7.8 调试", "7.9 软件可靠性"},
            {"8.1 软件维护的定义", "8.2 软件维护的特点", "8.3 软件维护过程", "8.4 软件的可维护性", "8.5 预防性维护", "8.6 软件再工程过程"},
            {"9.1 面向对象方法学概述", "9.2 面向对象的概念", "9.4 对象模型", "9.6 功能模型"},
            {"10.1 面向对象分析的基本过程", "10.3 建立对象模型"},
            {"11.1 面向对象设计的准则", "11.5 设计问题域子系统", "11.6 设计人机交互子系统", "11.7 设计任务管理子系统", "11.8 设计数据管理子系统"},
            {"12.1 程序设计语言"}

    };
    public String[][][] child_grandson= {
            {{"1.1 软件危机"},
                    {"AA","AAA"}},
            {{"1.2 软件工程"},
                    {"BBB","BBBB","BBBBB"}},
            {{"1.3 软件生命周期"},
                    {"CCC","CCCC"}},
            {{"1.4 软件过程"},
                    {"DDD","DDDD","DDDDD"}},
    };





    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.chapter_section);
        this.setTitle("软件工程目录");
        welcomeNameTv=(TextView)findViewById(R.id.welcome);
        btnNormal = (Button) findViewById(R.id.btnNormal);
        //为视频资料按钮设置事件
        btnNormal.setOnClickListener(new ClickEvent());
        backTv=(Button)findViewById(R.id.back3);
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TestExpendListView.this,MyActivity.class);
                startActivity(i);
            }
        });

        groupList=new ArrayList<String>();
        childList=new ArrayList<List<String>>();
        lessonList=new ArrayList<Lesson>();
        chapterList=new ArrayList<Chapter>();
        for(int i=0;i<groups.length;i++){
            groupList.add(groups[i]);
        }
        for(int i=0;i<child.length;i++){
            childList.add(Arrays.asList(child[i]));
        }






        adapter = new TreeViewAdapter(this,TreeViewAdapter.PaddingLeft>>1);
        expandableListView=(ExpandableListView)findViewById(R.id.expandedListView);

    }
//打开视频资料
    class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            adapter.RemoveAll();
            adapter.notifyDataSetChanged();

            if (view == btnNormal) {
                List<TreeViewAdapter.TreeNode> treeNode = adapter.getTreeNodes();
                for (int i = 0; i <groupList.size(); i++) {
                    TreeViewAdapter.TreeNode node = new TreeViewAdapter.TreeNode();
                    node.parent = groupList.get(i);
                    for (int ii = 0; ii < childList.get(i).size(); ii++) {
                        node.childs.add(child[i][ii]);
                    }
                    treeNode.add(node);

                        }




                adapter.UpdateTreeNode(treeNode);
                expandableListView.setAdapter(adapter);
                expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                        String str =  groups[groupPosition]  + child[groupPosition][childPosition];
                        Toast.makeText(TestExpendListView.this, str,
                                Toast.LENGTH_SHORT).show();
                        if(groupPosition==0&&childPosition==0){
                            Intent intent = new Intent(TestExpendListView.this, TeacherVideo.class);
                            startActivity(intent);
                        }else if(groupPosition==0&&childPosition==1){
                            Intent intent = new Intent(TestExpendListView.this, TeacherVideo2.class);
                            startActivity(intent);
                        }else if(groupPosition==0&&childPosition==2){
                            Intent intent = new Intent(TestExpendListView.this, TeacherVideo1_3.class);
                            startActivity(intent);

                        }else if(groupPosition==0&&childPosition==3){
                            Intent intent = new Intent(TestExpendListView.this, TeacherVideo4.class);
                            startActivity(intent);
                        }


                        //Intent intent = new Intent(TestExpendListView.this, ControllerActivity.class);

                        //String url=lessonList.get(groupPosition).getChapterName().getUrl();
                        //intent.putExtra("ks",url);

                        //startActivity(intent);
                        return true;
                    }
                });
            }
        }
    }

    }



