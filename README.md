# Project Setup

## Cloning the project

```shell
git clone https://github.com/JonArvy/Payroll
```

## VM Options

Go to the `Run` tab of IntelliJ and click on `Modify Options` and click on `Add VM Options`. Enter the following line of
command to the VM field

```shell
--module-path
"path/to/javafx/lib"
--add-modules
javafx.controls,javafx.fxml
```