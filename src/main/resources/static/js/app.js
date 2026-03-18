
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
