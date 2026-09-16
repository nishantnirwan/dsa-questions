# Write your MySQL query statement below
WITH t AS (
    SELECT visited_on, SUM(amount) AS amount
    FROM Customer
    GROUP BY visited_on
)
SELECT a.visited_on, SUM(b.amount) AS amount, ROUND(SUM(b.amount) / 7, 2) AS average_amount
FROM t a
JOIN t b
ON b.visited_on BETWEEN DATE_SUB(a.visited_on, INTERVAL 6 DAY)
AND a.visited_on
WHERE a.visited_on >= (
    SELECT DATE_ADD(MIN(visited_on), INTERVAL 6 DAY)
    FROM Customer
)
GROUP BY a.visited_on
ORDER BY a.visited_on;