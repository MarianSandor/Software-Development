package com.example.pcpartshop.repository.specification;

import com.example.pcpartshop.model.part.*;
import org.springframework.data.jpa.domain.Specification;

public class PartSpecification {

    public static Specification<CPU> brandLikeCPU(String brand) {
        return (root, query, cb) -> cb.like(root.get("brand"), "%" + brand + "%");
    }

    public static Specification<CPU> modelLikeCPU(String model) {
        return (root, query, cb) -> cb.like(root.get("model"), "%" + model + "%");
    }

    public static Specification<GPU> brandLikeGPU(String brand) {
        return (root, query, cb) -> cb.like(root.get("brand"), "%" + brand + "%");
    }

    public static Specification<GPU> modelLikeGPU(String model) {
        return (root, query, cb) -> cb.like(root.get("model"), "%" + model + "%");
    }

    public static Specification<PSU> brandLikePSU(String brand) {
        return (root, query, cb) -> cb.like(root.get("brand"), "%" + brand + "%");
    }

    public static Specification<PSU> modelLikePSU(String model) {
        return (root, query, cb) -> cb.like(root.get("model"), "%" + model + "%");
    }

    public static Specification<Motherboard> brandLikeMotherboard(String brand) {
        return (root, query, cb) -> cb.like(root.get("brand"), "%" + brand + "%");
    }

    public static Specification<Motherboard> modelLikeMotherboard(String model) {
        return (root, query, cb) -> cb.like(root.get("model"), "%" + model + "%");
    }

    public static Specification<Memory> brandLikeMemory(String brand) {
        return (root, query, cb) -> cb.like(root.get("brand"), "%" + brand + "%");
    }

    public static Specification<Memory> modelLikeMemory(String model) {
        return (root, query, cb) -> cb.like(root.get("model"), "%" + model + "%");
    }

    public static Specification<PcCase> brandLikePcCase(String brand) {
        return (root, query, cb) -> cb.like(root.get("brand"), "%" + brand + "%");
    }

    public static Specification<PcCase> modelLikePcCase(String model) {
        return (root, query, cb) -> cb.like(root.get("model"), "%" + model + "%");
    }

    public static Specification<Storage> brandLikeStorage(String brand) {
        return (root, query, cb) -> cb.like(root.get("brand"), "%" + brand + "%");
    }

    public static Specification<Storage> modelLikeStorage(String model) {
        return (root, query, cb) -> cb.like(root.get("model"), "%" + model + "%");
    }

}
