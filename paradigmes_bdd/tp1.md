# TP1 - Opérations avec Redis

## Partie 1

### 1.)
```bash
SET KEY1 "VALUE1"
```

### 2.)
```bash
SET KEY2 2
INCRBY KEY2 5
```

### 3.)
```bash
LPUSH mylist1 "A" "B" "C"
BLMOVE mylist1 mylist2 LEFT RIGHT
LRANGE mylist2 0 -1
```

### 4.)
```bash
ZADD rankings 100 "player1" 200 "player2" 150 "player3"
ZRANGE rankings 0 -1 WITHSCORES
```

### 5.)
```bash
HSET book:1 title "1984" author "George Orwell" publisher "Secker & Warburg" year 1949
HGETALL book:1
```

### 6.)
```bash
SET tempkey "temporary value"
EXPIRE tempkey 60
```

### 7.)
```bash
SET mykey "value"
MOVE mykey 1
``` 

### 8.)
```bash
MSET key1 "value1" key2 "value2" key3 "value3"
``` 

### 9.)
```bash
SUBSCRIBE channel1
```
Dans une autre session Redis CLI :
```bash
PUBLISH channel1 "Hello from Redis!"
```

### 10.)
```bash
HMSET student:100 name "John Doe" address "123 Main St" dob "2000-01-01" courses "Math,Science"
HGETALL student:100
```

### 11.)
```bash
WATCH student:100
MULTI
HSET student:100 name "John Smith"
EXEC
```

### 12.)
```bash
HSET student:101 name "Jane Doe" address "456 Elm St"
HGET student:101 name
```

## Partie 2

### 1.)
