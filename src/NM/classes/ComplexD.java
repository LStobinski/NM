package NM.classes;

public class ComplexD {
    final double real;
    final double imaginary;

    public ComplexD(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexD add(ComplexD other) {
        return new ComplexD(this.real + other.real, this.imaginary + other.imaginary);
    }

    public ComplexD mul(ComplexD other) {
        return new ComplexD(
                this.real * other.real - this.imaginary * other.imaginary,
                other.real * other.imaginary + this.imaginary * other.real
        );
    }

    public String toString() {
        return String.format("{%.16f + %.16fi}", real, imaginary);
    }
}
