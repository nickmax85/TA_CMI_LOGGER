select sensor.id 'SensorId',sensor.adr 'Sensor Adresse', measure.id 'Messwert Id', measure.value 'Messwert', unit.sign 'Einheit', location.name 'Einbauort'
from sensor, unit, measure, location
where sensor.unit_id = unit.id && sensor.id = measure.sensor_id && sensor.location_id = location.id && sensor.adr = 10;




SELECT sensor.name 'Name', location.name ' Einbauort',  unit.name 'Messart',  avg(measure.value) 'Durchschnittswerte',  unit.sign 'Einheit'  FROM sensor, measure, location, unit
where sensor.id = measure.sensor_id && sensor.location_id = location.id && sensor.unit_id = unit.id
group by sensor_id;


