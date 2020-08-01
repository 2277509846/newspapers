import axios from "axios"

export function request(config) {
	const req = axios.create({
		baseURL: "/api/"
	})
	return req(config);
}