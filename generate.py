import os

base_dir = r"c:/Users/User/Documents/mwimule/UR-CST/SEMESTER 2/DATABASE PROGRAMMMING/java"

def ensure_dir(path):
    os.makedirs(os.path.join(base_dir, path), exist_ok=True)

ensure_dir("src/main/java/com/example/model")
ensure_dir("src/main/java/com/example/repository")
ensure_dir("src/main/java/com/example/service")
ensure_dir("src/main/java/com/example/controller")
ensure_dir("src/main/resources/static/js")

# 1. pom.xml
pom_xml = """<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>db-programming-assignment</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>db-programming-assignment</name>
    <description>DB Programming Assignment</description>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
"""
with open(os.path.join(base_dir, "pom.xml"), "w") as f:
    f.write(pom_xml)

# 2. application.properties
app_props = """spring.datasource.url=jdbc:mysql://db:3306/securities_exchange_management?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
"""
with open(os.path.join(base_dir, "src/main/resources/application.properties"), "w") as f:
    f.write(app_props)

# 3. Main Application Class
app_java = """package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
"""
with open(os.path.join(base_dir, "src/main/java/com/example/Application.java"), "w") as f:
    f.write(app_java)

# 4. entities
entities = [
    {
        "name": "Exchange", "table": "Exchanges", "id": "exchangeId", "id_col": "exchange_id",
        "fields": [
            ("Integer", "exchangeId", "exchange_id"),
            ("String", "name", "name"),
            ("String", "location", "location"),
            ("java.time.LocalTime", "tradingStart", "trading_start"),
            ("java.time.LocalTime", "tradingEnd", "trading_end")
        ]
    },
    {
        "name": "Security", "table": "Securities", "id": "securityId", "id_col": "security_id",
        "fields": [
            ("Integer", "securityId", "security_id"),
            ("Integer", "exchangeId", "exchange_id"),
            ("String", "symbol", "symbol"),
            ("String", "name", "name"),
            ("String", "type", "type")
        ]
    },
    {
        "name": "Participant", "table": "Participants", "id": "participantId", "id_col": "participant_id",
        "fields": [
            ("Integer", "participantId", "participant_id"),
            ("String", "name", "name"),
            ("String", "address", "address"),
            ("String", "contactDetails", "contact_details")
        ]
    },
    {
        "name": "OrderEntity", "table": "Orders", "id": "orderId", "id_col": "order_id",
        "fields": [
            ("Integer", "orderId", "order_id"),
            ("Integer", "participantId", "participant_id"),
            ("Integer", "securityId", "security_id"),
            ("String", "orderType", "order_type"),
            ("Integer", "quantity", "quantity"),
            ("java.math.BigDecimal", "price", "price"),
            ("java.time.LocalDateTime", "orderTimestamp", "order_timestamp")
        ]
    },
    {
        "name": "Trade", "table": "Trades", "id": "tradeId", "id_col": "trade_id",
        "fields": [
            ("Integer", "tradeId", "trade_id"),
            ("Integer", "buyOrderId", "buy_order_id"),
            ("Integer", "sellOrderId", "sell_order_id"),
            ("Integer", "quantity", "quantity"),
            ("java.math.BigDecimal", "price", "price"),
            ("java.time.LocalDateTime", "tradeTimestamp", "trade_timestamp")
        ]
    },
    {
        "name": "Position", "table": "Positions", "id": "positionId", "id_col": "position_id",
        "fields": [
            ("Integer", "positionId", "position_id"),
            ("Integer", "participantId", "participant_id"),
            ("Integer", "securityId", "security_id"),
            ("Integer", "quantity", "quantity"),
            ("java.math.BigDecimal", "averagePrice", "average_price"),
            ("java.time.LocalDate", "entryDate", "entry_date"),
            ("java.time.LocalDate", "exitDate", "exit_date")
        ]
    },
    {
        "name": "Blocker", "table": "Blockers", "id": "blockerId", "id_col": "blocker_id",
        "fields": [
            ("Integer", "blockerId", "blocker_id"),
            ("String", "name", "name")
        ]
    },
    {
        "name": "BlockingPolicy", "table": "BlockingPolicies", "id": "policyId", "id_col": "policy_id",
        "fields": [
            ("Integer", "policyId", "policy_id"),
            ("Integer", "blockerId", "blocker_id"),
            ("Integer", "participantId", "participant_id"),
            ("Integer", "securityId", "security_id"),
            ("String", "restrictionType", "restriction_type"),
            ("java.time.LocalDate", "effectiveFrom", "effective_from"),
            ("java.time.LocalDate", "effectiveTo", "effective_to")
        ]
    }
]

for ent in entities:
    # Model
    model_code = f"package com.example.model;\n\nimport jakarta.persistence.*;\n\n@Entity\n@Table(name = \\\"{ent['table']}\\\")\npublic class {ent['name']} {{\n"
    for ftype, fname, fcol in ent['fields']:
        if fname == ent['id']:
            model_code += f"    @Id\n    @GeneratedValue(strategy = GenerationType.IDENTITY)\n"
        model_code += f"    @Column(name = \\\"{fcol}\\\")\n    private {ftype.split('.')[-1]} {fname};\n"
    model_code += "\n    // Getters and Setters\n"
    for ftype, fname, fcol in ent['fields']:
        cap_fname = fname[0].upper() + fname[1:]
        short_type = ftype.split('.')[-1]
        model_code += f"    public {short_type} get{cap_fname}() {{ return {fname}; }}\n"
        model_code += f"    public void set{cap_fname}({short_type} {fname}) {{ this.{fname} = {fname}; }}\n"
    model_code += "}\n"
    with open(os.path.join(base_dir, f"src/main/java/com/example/model/{ent['name']}.java"), "w") as f:
        f.write(model_code)

    # Repository
    repo_code = f"""package com.example.repository;

import com.example.model.{ent['name']};
import org.springframework.data.jpa.repository.JpaRepository;

public interface {ent['name']}Repository extends JpaRepository<{ent['name']}, Integer> {{
}}
"""
    with open(os.path.join(base_dir, f"src/main/java/com/example/repository/{ent['name']}Repository.java"), "w") as f:
        f.write(repo_code)

    # Service
    service_code = f"""package com.example.service;

import com.example.model.{ent['name']};
import com.example.repository.{ent['name']}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class {ent['name']}Service {{

    @Autowired
    private {ent['name']}Repository repository;

    public List<{ent['name']}> findAll() {{
        return repository.findAll();
    }}

    public Optional<{ent['name']}> findById(Integer id) {{
        return repository.findById(id);
    }}

    public {ent['name']} save({ent['name']} entity) {{
        return repository.save(entity);
    }}

    public void deleteById(Integer id) {{
        repository.deleteById(id);
    }}
}}
"""
    with open(os.path.join(base_dir, f"src/main/java/com/example/service/{ent['name']}Service.java"), "w") as f:
        f.write(service_code)

    # Controller
    path = ent['name'].lower()
    if path == "orderentity":
        path = "orders"
    controller_code = f"""package com.example.controller;

import com.example.model.{ent['name']};
import com.example.service.{ent['name']}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{path}")
public class {ent['name']}Controller {{

    @Autowired
    private {ent['name']}Service service;

    @GetMapping
    public List<{ent['name']}> getAll() {{
        return service.findAll();
    }}

    @GetMapping("/{{id}}")
    public ResponseEntity<{ent['name']}> getById(@PathVariable Integer id) {{
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }}

    @PostMapping
    public {ent['name']} create(@RequestBody {ent['name']} entity) {{
        return service.save(entity);
    }}

    @PutMapping("/{{id}}")
    public ResponseEntity<{ent['name']}> update(@PathVariable Integer id, @RequestBody {ent['name']} entity) {{
        return service.findById(id).map(existing -> {{
"""
    for ftype, fname, fcol in ent['fields']:
        if fname != ent['id']:
            cap_fname = fname[0].upper() + fname[1:]
            controller_code += f"            existing.set{cap_fname}(entity.get{cap_fname}());\n"
    controller_code += f"""            return ResponseEntity.ok(service.save(existing));
        }}).orElse(ResponseEntity.notFound().build());
    }}

    @DeleteMapping("/{{id}}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {{
        if (service.findById(id).isPresent()) {{
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }}
        return ResponseEntity.notFound().build();
    }}
}}
"""
    with open(os.path.join(base_dir, f"src/main/java/com/example/controller/{ent['name']}Controller.java"), "w") as f:
        f.write(controller_code)

# 5. HTML Frontend
html_code = """<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Database Programming App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { padding-top: 20px; }
        .sidebar { background-color: #f8f9fa; padding: 15px; min-height: 100vh; }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 sidebar">
            <h4>Entities</h4>
            <div class="list-group" id="entity-menu">
                <a href="#" class="list-group-item list-group-item-action" onclick="loadEntity('exchange')">Exchanges</a>
                <a href="#" class="list-group-item list-group-item-action" onclick="loadEntity('security')">Securities</a>
                <a href="#" class="list-group-item list-group-item-action" onclick="loadEntity('participant')">Participants</a>
                <a href="#" class="list-group-item list-group-item-action" onclick="loadEntity('orders')">Orders</a>
                <a href="#" class="list-group-item list-group-item-action" onclick="loadEntity('trade')">Trades</a>
                <a href="#" class="list-group-item list-group-item-action" onclick="loadEntity('position')">Positions</a>
                <a href="#" class="list-group-item list-group-item-action" onclick="loadEntity('blocker')">Blockers</a>
                <a href="#" class="list-group-item list-group-item-action" onclick="loadEntity('blockingpolicy')">BlockingPolicies</a>
            </div>
        </nav>
        <main class="col-md-10">
            <h2 id="entity-title">Welcome</h2>
            <button class="btn btn-primary mb-3" id="btn-create" style="display:none;" onclick="showForm()">Create New</button>
            <div id="table-container">Select an entity from the sidebar to manage it.</div>
            
            <!-- Modal Form -->
            <div class="modal fade" id="formModal" tabindex="-1">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="formModalLabel">Form</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                  </div>
                  <div class="modal-body" id="form-body">
                    <!-- Form fields injected here -->
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="saveRecord()">Save changes</button>
                  </div>
                </div>
              </div>
            </div>

        </main>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>
"""
with open(os.path.join(base_dir, "src/main/resources/static/index.html"), "w") as f:
    f.write(html_code)

js_code = """
let currentEntity = null;
const modal = new bootstrap.Modal(document.getElementById('formModal'));
let fieldsMap = {
    'exchange': ['exchangeId','name','location','tradingStart','tradingEnd'],
    'security': ['securityId','exchangeId','symbol','name','type'],
    'participant': ['participantId','name','address','contactDetails'],
    'orders': ['orderId','participantId','securityId','orderType','quantity','price','orderTimestamp'],
    'trade': ['tradeId','buyOrderId','sellOrderId','quantity','price','tradeTimestamp'],
    'position': ['positionId','participantId','securityId','quantity','averagePrice','entryDate','exitDate'],
    'blocker': ['blockerId','name'],
    'blockingpolicy': ['policyId','blockerId','participantId','securityId','restrictionType','effectiveFrom','effectiveTo']
};

function loadEntity(entityName) {
    currentEntity = entityName;
    document.getElementById('entity-title').innerText = entityName.toUpperCase();
    document.getElementById('btn-create').style.display = 'block';
    fetchData();
}

function fetchData() {
    fetch('/api/' + currentEntity)
        .then(res => res.json())
        .then(data => {
            renderTable(data);
        });
}

function renderTable(data) {
    let fields = fieldsMap[currentEntity];
    if (data.length === 0) {
        document.getElementById('table-container').innerHTML = '<p>No records found.</p>';
        return;
    }
    let html = '<table class="table table-striped"><thead><tr>';
    fields.forEach(f => html += `<th>${f}</th>`);
    html += '<th>Actions</th></tr></thead><tbody>';
    
    data.forEach(row => {
        html += '<tr>';
        fields.forEach(f => html += `<td>${row[f] || ''}</td>`);
        html += `<td>
            <button class="btn btn-sm btn-info" onclick='editRecord(${JSON.stringify(row)})'>Edit</button>
            <button class="btn btn-sm btn-danger" onclick="deleteRecord(${row[fields[0]]})">Delete</button>
        </td></tr>`;
    });
    html += '</tbody></table>';
    document.getElementById('table-container').innerHTML = html;
}

function showForm(record = null) {
    let fields = fieldsMap[currentEntity];
    let html = '<form id="entity-form">';
    fields.forEach(f => {
        let isId = f.endsWith('Id') && f === fields[0];
        let val = record ? record[f] : '';
        if (val === null || val === undefined) val = '';
        html += `<div class="mb-3">
            <label class="form-label">${f}</label>
            <input type="${f.includes('Date') ? 'date' : f.includes('Time') ? 'datetime-local' : 'text'}" 
                class="form-control" id="${f}" ${isId ? 'readonly' : ''} value="${val}">
        </div>`;
    });
    html += '</form>';
    document.getElementById('form-body').innerHTML = html;
    modal.show();
}

function editRecord(record) {
    showForm(record);
}

function saveRecord() {
    let fields = fieldsMap[currentEntity];
    let payload = {};
    fields.forEach(f => {
        let val = document.getElementById(f).value;
        if (val !== '') payload[f] = val;
    });
    
    let isEditing = !!payload[fields[0]];
    let url = '/api/' + currentEntity + (isEditing ? '/' + payload[fields[0]] : '');
    let method = isEditing ? 'PUT' : 'POST';
    
    fetch(url, {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(payload)
    }).then(res => {
        modal.hide();
        fetchData();
    });
}

function deleteRecord(id) {
    if(confirm('Are you sure?')) {
        fetch('/api/' + currentEntity + '/' + id, { method: 'DELETE' })
            .then(() => fetchData());
    }
}
"""
with open(os.path.join(base_dir, "src/main/resources/static/js/app.js"), "w") as f:
    f.write(js_code)

# 6. Dockerfile
dockerfile_code = """FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests
ENTRYPOINT ["java","-jar","target/db-programming-assignment-0.0.1-SNAPSHOT.jar"]
"""
with open(os.path.join(base_dir, "Dockerfile"), "w") as f:
    f.write(dockerfile_code)

# 7. docker-compose.yml
docker_compose_code = """version: '3.8'
services:
  db:
    image: mysql:8.0
    container_name: mysql_db
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: securities_exchange_management
    ports:
      - "3306:3306"
    volumes:
      - ./db.sql:/docker-entrypoint-initdb.d/init.sql
  app:
    build: .
    container_name: springboot_app
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/securities_exchange_management?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
"""
with open(os.path.join(base_dir, "docker-compose.yml"), "w") as f:
    f.write(docker_compose_code)

print("Project generated successfully.")
