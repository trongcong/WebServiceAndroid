<?php
DEFINE('DB_USER', 'root');
DEFINE('DB_PASSWORD', '');
DEFINE('DB_HOST', '127.0.0.1');
DEFINE('DB_NAME', 'students_manager');
$mysqli = @mysql_connect(DB_HOST, DB_USER, DB_PASSWORD) OR die('Could not connect to MySQL');
@mysql_select_db(DB_NAME) OR die('Could not select the database');
mysql_query("SET NAMES 'utf8'");


if (isset($_POST["name"], $_POST["age"], $_POST["nclass"])) {
    $n = trim($_POST["name"]);
    $a = trim($_POST["age"]);
    $c = trim($_POST["nclass"]);
    if (empty($n) || empty($a) || empty($c)) {
        echo ("Vui lòng nhập đầy đủ thông tin");
    } else {
        //$query = "INSERT INTO students (name, age, nclass) VALUES ('" . $_POST["name"] . "', '" . $_POST["age"] . "', '" . $_POST["nclass"] . "')";
        $query = "INSERT INTO students (name, age, nclass) VALUES ('" . $n . "', '" . $a . "', '" . $c . "')";
        $res = mysql_query($query);
        if ($res) {
            echo ("Thêm sinh viên thành công");
        } else {
            echo ("Thêm sinh viên thất bại");
        }
    }
    goto end;
} else if (isset($_GET["id"])) {
    if ($_GET["id"] == "") {
        echo ("Vui lòng nhập id");
        goto end;
    } else {
        $query = "SELECT * FROM students WHERE id='" . $_GET["id"] . "'";
        $resouter = mysql_query($query);
    }
} else {
    $query = "SELECT * FROM students";
    $resouter = mysql_query($query);
}
$temparray = array();
$total_records = mysql_num_rows($resouter);
if ($total_records >= 1) {
    while ($row = mysql_fetch_assoc($resouter)) {
        $temparray[] = $row;
    }
}
echo json_encode($temparray);
end:
?>