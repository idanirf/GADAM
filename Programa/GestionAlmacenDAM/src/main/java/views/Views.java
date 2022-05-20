package views;

public enum Views {
    MENU_MANAGER("views/MenuManager.fxml"),
    MENU_EMPLEADO("views/MenuEmployee.fxml"),
    EMPLEADOS("views/EmpleadosVistaManager.fxml"),
    NUEVO_EMPLEADO("views/NuevoEmpleadosVistaManager.fxml"),
    EDITAR_EMPLEADO("views/EditarEmpledosVistaManager.fxml"),
    ACERCA_DE("views/AcercaDe.fxml");
    private final String view;

    Views(String view) {
        this.view = view;
    }

    public String get() {
        return view;
    }
}
