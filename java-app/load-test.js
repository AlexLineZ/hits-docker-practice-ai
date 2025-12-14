import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '30s', target: 10 },   // разогрев
        { duration: '1m', target: 50 },    // основная нагрузка
        { duration: '30s', target: 0 },    // спад
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'],  // 95% запросов < 500мс
        http_req_failed: ['rate<0.01'],    // <1% ошибок
    },
};

const BASE_URL = 'http://localhost:8080';

export default function () {
    // 80% read
    http.get(`${BASE_URL}/api/laptops`);
    http.get(`${BASE_URL}/api/monitors`);
    http.get(`${BASE_URL}/api/hdds`);
    http.get(`${BASE_URL}/api/pcs`);

    // 20% write
    const payload = JSON.stringify({
        diagonal: 24.0
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(`${BASE_URL}/api/monitors/add`, payload, params);

    check(res, {
        'status is 200': (r) => r.status === 200,
    });

    sleep(1);
}