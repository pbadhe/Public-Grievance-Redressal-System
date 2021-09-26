# Public-Grievance-Redressal-System

- An online portal built to overcome the loopholes in the existing municipal grievance redressal systems of India.
- Streamlined execution of complaints using entities from real-life scenario; modelled into a database using relationships governed by business rules.
- Built using **SQL** triggers to send important notifications to the involved entities with support for offline functionality.
- Geotagging in complaints and location based analysis using **JxMaps**.
- UI built using Java Swing.

## Developed By
- Pranav Badhe (badhepranav@gmail.com)
- Tejas Abhang (tejas.5jan@gmail.com)
- Siddhi Dhonsale (siddhi.d123@gmail.com)

# Installation & Usage
The project will require an SQL database to be configured on the host. Database schema and sample default data is included in the **river_mini.sql** file.

Import the sql file into the database configured on the host. The project code can be directly imported into any modern Java IDE for editing the database configurations (not modular for now).
The project will generate a runnable jar post compilation, which can be directly used as a standalone application ready for deployment. 

Note: Some API changes to JxMaps may be required according to the use of the host (100 requests per day limit).
