package dataManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {
    private ObservableList<String> dataList;

    public DataModel() {
        dataList = FXCollections.observableArrayList();
    }

    public ObservableList<String> getDataList() {
        return dataList;
    }

    public void addData(String data) {
        dataList.add(data);
    }

    public void updateData(int index, String newData) {
        dataList.set(index, newData);
    }

    public void removeData(String data) {
        dataList.remove(data);
    }

    public int findData(String dataToFind) {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).equals(dataToFind)) {
                return i; // Return the index of the found data
            }
        }
        return -1; // Return -1 if data is not found
    }
}
