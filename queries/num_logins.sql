SELECT u.username, q.logins FROM user AS u, (SELECT userid, count(*) AS logins FROM user_login_history GROUP BY userid) AS q WHERE u.userid = q.userid ORDER BY q.logins DESC;

