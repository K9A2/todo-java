package com.stormlin.command;

import com.stormlin.entity.Task;
import com.stormlin.entity.TodoList;
import com.stormlin.util.Const;
import com.stormlin.util.Util;

import java.util.HashMap;

public class CommandRemove implements ICommand {

    public void usage() {
        System.out.println("Use this command to remove task specified by HEX ID.");
        System.out.println("usage: remove <HEX-ID>");
        System.out.println("   or: [-h|--help]");
    }

    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments!");
            System.out.println();
            usage();
            return;
        }

        if (args[1].equals("-h") || args[1].equals("--help")) {
            usage();
            return;
        }

        String key = args[1];

        TodoList todoList = Util.readFromListFile(Const.DEFAULT_FILE_PATH);
        if (todoList == null) {
            System.out.println("Unable to read the list file.");
            return;
        }

        HashMap<String, Task> taskMap = todoList.getCurrentBranch().getTaskList();
        if (taskMap.remove(key) == null) {
            System.out.println(String.format("Key: %s does not exists!", key));
        }
        System.out.println(String.format("Key: %s removed successfully!", key));
        Util.saveToListFile(todoList);
    }

}
