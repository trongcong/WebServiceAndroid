<?php
DEFINE('DB_USER', 'root');
DEFINE('DB_PASSWORD', '');
DEFINE('DB_HOST', '127.0.0.1');
DEFINE('DB_NAME', 'students_manager');
$mysqli = @mysql_connect(DB_HOST, DB_USER, DB_PASSWORD) OR die('Could not connect to MySQL');
@mysql_select_db(DB_NAME) OR die('Could not select the database');
mysql_query("SET NAMES 'utf8'");

if (isset($_GET["id"])) {
    if ($_GET["id"] == "" ) {
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