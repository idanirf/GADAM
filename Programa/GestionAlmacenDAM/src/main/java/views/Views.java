package views;

public enum Views {
    MENU_MANAGER("views/MenuManager.fxml"),
    MENU_EMPLEADO("views/MenuEmployee.fxml"),
    EMPLEADOS("views/")
    private final String view;

    Views(String view) {
        this.view = view;
    }

    public String get() {
        return view;
    }
}
