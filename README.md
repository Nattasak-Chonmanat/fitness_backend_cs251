# Fitness API

## How to install
```bash
git pull https://github.com/Nattasak-Chonmanat/fitness_backend_cs251.git
cd path/to/your/project
docker-compose up --build
```

อย่าลืมเปิด docker desktop ไว้ด้วย

## How to access database
ดาวโหลด MySQL workbench(หรือตัวไหนก็ได้แต่อันนี้จะสอนแค่ mysql workbench นะ) 
- คลิกเครื่องหมาย "+" ข้างMysql Connections แล้ว set ตามนี้
- Connection Name : Docker_Database
- HostName : 127.0.0.1 หรือ localhost
- Username : root
- password : คลิก Store in Vault พิมพ์ root คลิก ok
- คลิก Test connection
- ถ้าทุกอย่างถูกต้องจะขึ้น successfully made the connect
- กด ok

