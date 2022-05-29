package com.dam.gestionalmacendam.views;

public enum Views {
    MENU_MANAGER("views/MenuManager.fxml"),
    MENU_EMPLEADO("views/MenuEmployee.fxml"),
    TABLA_EMPLYEE("views/EmpleadosVistaManager.fxml"),
    MODIFY_EMPLOYEE("views/EditarEmpleadoVistaManager.fxml"),
    NEW_EMPLOYEE("views/NuevoEmpleadoVistaManager.fxml"),
    VIEW_CUSTOMER("views/CustomerView.fxml"),
    SHOW_DATA_CUSTOMER("views/ShowCustomerData.fxml"),
    MODIFY_DATA_CUSTOMER("views/ModifyDataCustomer.fxml"),
    ARTICLE_VIEW("views/article-View.fxml"),
    NEW_ARTICLE("views/nuevoProducto.fxml"),
    RESUME_ARTICLE("views/verDetalle.fxml"),
    RECEPTION_VIEW("views/receptionview.fxml"),
    SUPPLIER_VIEW("views/SuplierVistaManager.fxml"),
    MODIFY_DATA_SUPPLIER("views/SupplierModifyView.fxml"),

    ORDER_VIEW("views/OrderView.fxml"),
    LINE_ORDER_VIEW("views/lineaPedidoManagerView.fxml"),
    NEW_SUPPLIER_VIEW("views/suplierNewView.fxml"),
    ACERCA_DE("views/AcercaDe.fxml"),
    SPLASH("views/splash.fxml"),
    LOGIN("views/login.fxml"),
    REGISTER("views/register.fxml"),
    VIEW_ARTICLE("views/view-article.fxml"),
    VIEW_CARRITO("views/view-carrito.fxml"),
    MAIN_CUSTOMER("views/main-customer.fxml");

    private final String view;

    Views(String view) {
        this.view = view;
    }

    public String get() {
        return view;
    }
}
