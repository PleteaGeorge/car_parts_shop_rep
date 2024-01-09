
=====   brain-storming   =====

car_parts (id, name, price, marca)
clients (id, phone, name)
offers (id, client_id, car_parts_id, amount)

client->offer: oneToMany
