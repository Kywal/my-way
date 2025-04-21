import { NextAuthOptions } from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";
import axios from "axios";

export const authConfig: NextAuthOptions = {
  pages: {
    signIn: "/login",
    error: "/login",
  },
  providers: [
    CredentialsProvider({
      name: "Credentials",
      credentials: {
        email: { label: "Email", type: "text" },
        password: { label: "Senha", type: "password" },
      },
      async authorize(credentials) {
        try {
          const res = await axios.post("http://localhost:8081/user/login", {
            email: credentials?.email,
            password: credentials?.password,
          });

          console.log("LOGIN RESPONSE", res.status, res.data);

          const user = res.data;

          if (!user || !user.email) {
            return null;
          }

          return {
            id: user.email, // Fake ID pro NextAuth não quebrar
            email: user.email,
            role: user.role,
            name: user.person?.name,
          };
        } catch (error) {
          console.error("Erro ao autenticar:", error);

          return null;
        }
      },
    }),
  ],
  session: {
    strategy: "jwt",
  },
  callbacks: {
    async jwt({ token, user }) {
      if (user) {
        token.user = user;
      }

      return token;
    },
    async session({ session, token }) {
      if (token?.user) {
        session.user = token.user as any;
      }

      return session;
    },
    async redirect({ url, baseUrl }) {
      return "/dashboard";
    },
  },
  secret: process.env.AUTH_SECRET,
  useSecureCookies: false,
};
