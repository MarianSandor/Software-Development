from pcpartpicker import API
import mysql.connector
import random

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="root",
  database="pcpartshop"
)

mycursor = mydb.cursor()
api = API()

sql_insert_cpu = "INSERT INTO cpu (brand, model, cores, frequency, integrated_graphics, price, quantity) VALUES (%s, %s, %s, %s, %s, %s, %s)"
sql_delete_cpu = "DELETE FROM cpu"

sql_insert_gpu = "INSERT INTO gpu (brand, model, frequency, vram, price, quantity) VALUES (%s, %s, %s, %s, %s, %s)"
sql_delete_gpu = "DELETE FROM gpu"

sql_insert_memory = "INSERT INTO memory (brand, model, frequency, size, type, price, quantity) VALUES (%s, %s, %s, %s, %s, %s, %s)"
sql_delete_memory = "DELETE FROM memory"

sql_insert_motherboard = "INSERT INTO motherboard (brand, model, form_factor, socket, price, quantity) VALUES (%s, %s, %s, %s, %s, %s)"
sql_delete_motherboard = "DELETE FROM motherboard"

sql_insert_storage = "INSERT INTO storage (brand, model, capacity, type, price, quantity) VALUES (%s, %s, %s, %s, %s, %s)"
sql_delete_storage = "DELETE FROM storage"

sql_insert_psu = "INSERT INTO psu (brand, model, efficiency, form_factor, wattage, price, quantity) VALUES (%s, %s, %s, %s, %s, %s, %s)"
sql_delete_psu = "DELETE FROM psu"

sql_insert_case = "INSERT INTO pc_case (brand, model, form_factor, price, quantity) VALUES (%s, %s, %s, %s, %s)"
sql_delete_case = "DELETE FROM pc_case"


components = {
	"cpu": "cpu",
	"gpu": "video-card",
	"motherboard": "motherboard",
	"storage": "internal-hard-drive",
	"case": "case",
	"memory": "memory",
	"psu": "power-supply"
}

#Bootsrap CPU
print("Bootstrapping cpu...")
mycursor.execute(sql_delete_cpu)
entities = []
data = api.retrieve(components["cpu"])
for parts in data.values():
	for part in parts:
		if part and part.base_clock and part.price:
			entity = (part.brand, part.model, part.cores, "{:.1f}".format(part.base_clock.ghz), part.integrated_graphics, str(part.price.amount), random.randint(0, 5))
			entities.append(entity)
mycursor.executemany(sql_insert_cpu, entities)
print("Done")

#Bootsrap GPU
print("Bootstrapping gpu...")
mycursor.execute(sql_delete_gpu)
entities = []
data = api.retrieve(components["gpu"])
for parts in data.values():
	for part in parts:
		if part and part.core_clock and part.vram and part.price:
			entity = (part.brand, part.model, part.core_clock.mhz, part.vram.gb, str(part.price.amount), random.randint(0, 5))
			entities.append(entity)
mycursor.executemany(sql_insert_gpu, entities)
print("Done")

#Bootsrap Memory
print("Bootstrapping memory...")
mycursor.execute(sql_delete_memory)
entities = []
data = api.retrieve(components["memory"])
for parts in data.values():
	for part in parts:
		if part and part.speed and part.module_size and part.price:
			entity = (part.brand, part.model, part.speed.mhz, part.module_size.gb, part.module_type, str(part.price.amount), random.randint(0, 5))
			entities.append(entity)
mycursor.executemany(sql_insert_memory, entities)
print("Done")

#Bootsrap Motherboard
print("Bootstrapping motherboard...")
mycursor.execute(sql_delete_motherboard)
entities = []
data = api.retrieve(components["motherboard"])
for parts in data.values():
	for part in parts:
		if part and part.price:
			entity = (part.brand, part.model, part.form_factor, part.socket, str(part.price.amount), random.randint(0, 5))
			entities.append(entity)
mycursor.executemany(sql_insert_motherboard, entities)
print("Done")

#Bootsrap Storage
print("Bootstrapping storage...")
mycursor.execute(sql_delete_storage)
entities = []
data = api.retrieve(components["storage"])
for parts in data.values():
	for part in parts:
		if part and part.capacity and part.price:
			entity = (part.brand, part.model, part.capacity.gb, part.storage_type, str(part.price.amount), random.randint(0, 5))
			entities.append(entity)
mycursor.executemany(sql_insert_storage, entities)
print("Done")

#Bootsrap PSU
print("Bootstrapping psu...")
mycursor.execute(sql_delete_psu)
entities = []
data = api.retrieve(components["psu"])
for parts in data.values():
	for part in parts:
		if part and part.price:
			entity = (part.brand, part.model, part.efficiency_rating, part.form_factor, part.wattage, str(part.price.amount), random.randint(0, 5))
			entities.append(entity)
mycursor.executemany(sql_insert_psu, entities)
print("Done")

#Bootsrap Case
print("Bootstrapping case...")
mycursor.execute(sql_delete_case)
entities = []
data = api.retrieve(components["case"])
for parts in data.values():
	for part in parts:
		if part and part.price:
			entity = (part.brand, part.model, part.form_factor, str(part.price.amount), random.randint(0, 5))
			entities.append(entity)
mycursor.executemany(sql_insert_case, entities)
print("Done")

mydb.commit()