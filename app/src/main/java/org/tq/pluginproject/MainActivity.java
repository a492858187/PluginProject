package org.tq.pluginproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.tq.anchors.StartUp;
import org.tq.anchors.StartUpManager;
import org.tq.anchors.StartupSortStore;
import org.tq.anchors.TopologySort;
import org.tq.pluginproject.startup.Task1;
import org.tq.pluginproject.startup.Task2;
import org.tq.pluginproject.startup.Task3;
import org.tq.pluginproject.startup.Task4;
import org.tq.pluginproject.startup.Task5;
import org.tq.router.annotations.Destination;

import java.util.ArrayList;
import java.util.List;

@Destination(url = "router://page-home", description = "首页")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        List<StartUp<?>> list = new ArrayList<>();
//        list.add(new Task4());
//        list.add(new Task5());
//        list.add(new Task3());
//        list.add(new Task2());
//        list.add(new Task1());
//
//        StartupSortStore startupSortStore = TopologySort.sort(list);
//        List<StartUp<?>> result = startupSortStore.getResult();

    }
}
