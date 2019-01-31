export interface IAuth_logs {
    id?: string;
    user_id?: number;
    ip_address?: string;
    device?: string;
    location?: string;
    user_agent?: string;
    auth_type?: string;
    device_status?: string;
}

export class Auth_logs implements IAuth_logs {
    constructor(
        public id?: string,
        public user_id?: number,
        public ip_address?: string,
        public device?: string,
        public location?: string,
        public user_agent?: string,
        public auth_type?: string,
        public device_status?: string
    ) {}
}
